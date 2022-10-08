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

    fun changeFavorite(news: News) {
        if (_news.value == null)
            _news.value = mutableListOf()

        news.isFavorite = news.isFavorite.not()
        if (news.isFavorite && _news.value?.contains(news) == false) _news.value?.add(0, news)
        else _news.value?.remove(news)
        _news.value = _news.value
    }
}