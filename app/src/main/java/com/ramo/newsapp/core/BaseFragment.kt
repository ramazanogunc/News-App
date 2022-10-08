package com.ramo.newsapp.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.color.MaterialColors
import com.ramo.newsapp.BR
import com.ramo.newsapp.R
import com.ramo.newsapp.core.ext.findGenericWithType
import com.ramo.newsapp.core.ext.observeExt
import com.ramo.newsapp.core.ext.safeContext
import com.ramo.newsapp.core.state.DialogEvent
import com.ramo.newsapp.core.state.NavEvent
import com.ramo.newsapp.cusomviews.LoadingDialog

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int
) : Fragment() {

    protected lateinit var viewModel: VM
    protected var binding: DB? = null

    protected open fun isSharedViewModel(): Boolean = false
    private val exceptionDialog by lazy {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.error))
        }
    }
    protected val loadingDialog by lazy { LoadingDialog(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding?.lifecycleOwner = viewLifecycleOwner
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initCommonObserver()
        init()
    }

    override fun onDestroyView() {
        removeStatusBarColor()
        binding = null
        super.onDestroyView()
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
        val vmClass = javaClass.findGenericWithType<VM>(1)
        viewModel = ViewModelProvider(
            if (isSharedViewModel()) requireActivity() else this
        )[vmClass]
        binding?.setVariable(BR.vm, viewModel)
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

    protected fun safeBinding(block: DB.() -> Unit) {
        val safeBinding = binding ?: return
        with(safeBinding, block)
    }

    protected fun changeStatusBarColor(view: View?) {
        view ?: return
        val window = activity?.window ?: return
        val color = MaterialColors.getColor(view, androidx.appcompat.R.attr.colorPrimary)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        @Suppress("DEPRECATION")
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = color
    }

    protected fun removeStatusBarColor() {
        val window = activity?.window ?: return
        window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        @Suppress("DEPRECATION")
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    protected abstract fun init()
}