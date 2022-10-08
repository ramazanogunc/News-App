package com.ramo.newsapp.core.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter


object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("visible")
    fun changeVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("onSafeClick")
    fun View.onSafeClick(onClick: () -> Unit) {
        setOnClickListener(SafeClickListener {
            onClick()
        })
    }
}