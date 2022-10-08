package com.ramo.newsapp.core.bindingadapters

import androidx.databinding.BindingAdapter
import com.ramo.sweetrecycler.SweetRecyclerView

object SweetRecyclerViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("bind:list")
    fun <T> SweetRecyclerView.list(list: List<T>) {
        if (isPaginationEnable) addData(list)
        else setData(list)

    }
}