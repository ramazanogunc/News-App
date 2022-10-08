package com.ramo.newsapp.core.ext

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@SuppressLint("CheckResult")
fun ImageView.loadImage(
    url: String,
    @DrawableRes placeHolder: Int? = null,
    @DrawableRes error: Int? = null,
    cornerRadius: Int = 0
) {
    val options = RequestOptions()
    val transformList = mutableListOf<BitmapTransformation>(CenterCrop())
    if (placeHolder != null)
        options.placeholder(placeHolder)
    if (error != null)
        options.error(error)
    if (cornerRadius != 0)
        transformList.add(RoundedCorners(cornerRadius))

    Glide
        .with(this)
        .load(url)
        .apply(options)
        .transform(*transformList.toTypedArray())
        .into(this)
}