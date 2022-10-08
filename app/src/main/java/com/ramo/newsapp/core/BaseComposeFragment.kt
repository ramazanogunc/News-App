package com.ramo.newsapp.core

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ramo.newsapp.R
import com.ramo.newsapp.core.ext.findGenericWithType
import com.ramo.newsapp.core.ext.observeExt
import com.ramo.newsapp.core.ext.safeContext
import com.ramo.newsapp.core.state.DialogEvent
import com.ramo.newsapp.core.state.NavEvent
import com.ramo.newsapp.cusomviews.LoadingDialog

abstract class BaseComposeFragment<VM : BaseViewModel> : Fragment(R.layout.fragment_compose) {

    protected lateinit var viewModel: VM

    protected open fun isSharedViewModel(): Boolean = false
    private val exceptionDialog by lazy {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.error))
        }
    }
    protected val loadingDialog by lazy { LoadingDialog(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        content()
        initViewModel()
        initCommonObserver()
    }

    private fun initCommonObserver() {
        observeExt(viewModel.isLoading) { isLoading -> onChangeLoading(isLoading) }
        observeExt(viewModel.dialogEvent) { dialogEvent ->
            dismissAlertAndException()
            if (dialogEvent is DialogEvent.Alert)
                onAlert(dialogEvent.messageResId, dialogEvent.cancelable)
            else if (dialogEvent is DialogEvent.Error)
                onException(dialogEvent.throwable, dialogEvent.cancelable)

        }
        observeExt(viewModel.navigationEvent) { navEvent -> onNavigate(navEvent) }
    }

    private fun initViewModel() {
        val vmClass = javaClass.findGenericWithType<VM>(0)
        viewModel = ViewModelProvider(
            if (isSharedViewModel()) requireActivity() else this
        )[vmClass]
    }

    protected open fun onChangeLoading(isLoading: Boolean) {
        if (isLoading) loadingDialog.show()
        else loadingDialog.dismiss()
    }

    protected open fun onAlert(stringId: Int, cancelable: Boolean) {
        safeContext {
            Toast.makeText(it, getString(stringId), Toast.LENGTH_LONG).show()
        }
    }

    protected open fun onException(throwable: Throwable, cancelable: Boolean) {
        exceptionDialog.apply {
            setPositiveButton(null, null)
            setMessage(throwable.localizedMessage)
            setNegativeButton(getString(R.string.ok)) { _, _ ->
                if (cancelable.not()) activity?.onBackPressed()
            }
        }.show()
    }


    protected open fun dismissAlertAndException() {
        exceptionDialog.create().dismiss()
    }

    protected open fun onNavigate(navEvent: NavEvent) {
        when (navEvent) {
            is NavEvent.Navigate -> findNavController().navigate(navEvent.navDirections)
            NavEvent.GoBack -> activity?.onBackPressed()
        }
    }

    protected abstract fun content(): ComposeView

    fun composableView(
        content: @Composable () -> Unit
    ): ComposeView {
        val composeView = view?.findViewById<ComposeView>(R.id.composeView)
        composeView?.apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
            )
            setContent(content)
        }
        return composeView ?: throw IllegalStateException("Plase inflate fragment_compose file")
    }
}