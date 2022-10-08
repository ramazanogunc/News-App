package com.ramo.newsapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ramo.newsapp.core.BaseViewModel
import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : BaseViewModel() {

    private val _news = MutableLiveData<MutableList<News>>()
    val news: LiveData<MutableList<News>> get() = _news

    init {
        safeScope(loadingVisible = false) {

        }
    }

    fun addFavorite(news: News) {
        news.isFavorite = true
        if (_news.value == null)
            _news.value = mutableListOf(news)
        else
            _news.value?.add(0, news)
        _news.value = _news.value
    }
}