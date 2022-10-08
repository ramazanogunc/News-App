package com.ramo.newsapp.core.bindingadapters

import androidx.databinding.BindingAdapter
import com.ramo.sweetrecycler.SweetRecyclerView

object SweetRecyclerViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("list")
    fun <T> SweetRecyclerView.list(list: List<T>?) {
        list ?: return
        if (isPaginationEnable) addData(list)
        else setData(list)

    }
}