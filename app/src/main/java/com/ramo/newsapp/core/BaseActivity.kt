package com.ramo.newsapp.core

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.ramo.newsapp.BR
import com.ramo.newsapp.R
import com.ramo.newsapp.core.ext.findGenericWithType
import com.ramo.newsapp.core.ext.observeExt
import com.ramo.newsapp.core.state.DialogEvent
import com.ramo.newsapp.core.state.NavEvent
import com.ramo.newsapp.cusomviews.LoadingDialog

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int
) : AppCompatActivity() {

    protected lateinit var viewModel: VM
    protected var binding: DB? = null

    private val loadingDialog by lazy { LoadingDialog(this) }
    private val exceptionDialog by lazy {
        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.error))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding?.lifecycleOwner = this
        binding = DataBindingUtil.setContentView(this, layoutId)
        initViewModel()
        initCommonObserver()
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    protected fun safeBinding(block: DB.() -> Unit) {
        val safeBinding = binding ?: return
        with(safeBinding, block)
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
        viewModel = ViewModelProvider(this)[vmClass]
        binding?.setVariable(BR.vm,viewModel)
    }

    protected open fun onChangeLoading(isLoading: Boolean) {
        if (isLoading) loadingDialog.show()
        else loadingDialog.dismiss()
    }

    protected open fun onAlert(stringId: Int, cancelable: Boolean) {
        Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show()
    }

    protected open fun onException(throwable: Throwable, cancelable: Boolean) {
        exceptionDialog.apply {
            setPositiveButton(null, null)
            setMessage(throwable.localizedMessage)
            setNegativeButton(getString(R.string.ok)) { _, _ ->
                if (cancelable.not()) onBackPressed()
            }
        }.show()
    }

    protected open fun dismissAlertAndException() {
        exceptionDialog.create().dismiss()
    }

    protected open fun onNavigate(navEvent: NavEvent) {}

    protected abstract fun init()
}