package com.ramo.newsapp.core.bindingadapters

import androidx.databinding.BindingAdapter
import com.ramo.sweetrecycler.SweetRecyclerView

object SweetRecyclerViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("dataList", "pagingEnabled", requireAll = false)
    fun <T> SweetRecyclerView.dataList(
        list: List<T>?,
        pagingEnabled: Boolean?
    ) {
        list ?: return
        val paging = pagingEnabled ?: false
        isPaginationEnable = paging
        setData(list)
    }
}