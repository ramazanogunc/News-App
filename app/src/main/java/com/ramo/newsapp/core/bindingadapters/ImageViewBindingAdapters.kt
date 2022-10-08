package com.ramo.newsapp.core.bindingadapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ramo.newsapp.core.ext.loadImage


object ImageViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl", "cornerRadius", requireAll = false)
    fun ImageView.imageUrl(url: String?, cornerRadius: Int?) {
        url ?: return
        this.loadImage(url, cornerRadius = cornerRadius ?: 0)
    }

    @JvmStatic
    @BindingAdapter("srcCompat")
    fun ImageView.bindSrcCompat(drawable: Drawable?) {
        setImageDrawable(drawable)
    }
}