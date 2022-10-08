package com.ramo.newsapp.ui.common.viewholder

import android.view.ViewGroup
import com.ramo.newsapp.R
import com.ramo.newsapp.databinding.ItemNewsBinding
import com.ramo.newsapp.domain.model.News
import com.ramo.sweetrecycler.DBSweetViewHolder

class NewsViewHolder(
    viewGroup: ViewGroup?
) : DBSweetViewHolder<ItemNewsBinding, News>(
    layoutId = R.layout.item_news,
    viewGroup = viewGroup
) {

    override fun bind(data: News) {
        binding.item = data
        binding.executePendingBindings()
    }

}