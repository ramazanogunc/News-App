package com.ramo.newsapp.core.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ramo.newsapp.core.ext.loadImage


object ImageViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("bind:imageUrl", "bind:cornerRadius", requireAll = false)
    fun ImageView.imageUrl(url: String?, cornerRadius: Int?) {
        url ?: return
        this.loadImage(url, cornerRadius = cornerRadius ?: 0)
    }
}