package com.ramo.newsapp.ui.newsdetail

import androidx.lifecycle.SavedStateHandle
import com.ramo.newsapp.core.BaseViewModelWithArgs
import com.ramo.newsapp.domain.model.News
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModelWithArgs<NewsDetailFragmentArgs>(
    savedStateHandle,
    NewsDetailFragmentArgs::fromSavedStateHandle
) {

    val news: News get() = args.news
}