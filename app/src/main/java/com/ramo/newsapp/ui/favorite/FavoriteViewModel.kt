package com.ramo.newsapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ramo.newsapp.core.BaseViewModel
import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.domain.usecase.ChangeFavoriteUseCase
import com.ramo.newsapp.domain.usecase.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val changeFavoritesUseCase: ChangeFavoriteUseCase,
) : BaseViewModel() {

    private val _news = MutableLiveData<MutableList<News>>()
    val news: LiveData<MutableList<News>> get() = _news


    fun getFavorites(deviceId: String) {
        if (_news.value.isNullOrEmpty().not()) return
        safeScope(loadingVisible = false) {
            val response = getFavoritesUseCase(deviceId)
            _news.value = response
        }
    }

    fun changeFavorite(deviceId: String, news: News) {
        safeScope {
            if (_news.value == null)
                _news.value = mutableListOf()

            changeFavoritesUseCase(ChangeFavoriteUseCase.Params(deviceId, news))
            findAndChangeStatus(news)
            _news.value = _news.value
        }
    }

    private fun findAndChangeStatus(news: News) {
        if (news.isFavorite) {
            _news.value?.add(0, news)
        } else {
            _news.value?.removeAll { it.diffId == news.diffId }
        }

    }

    fun detectFavoriteStatus(news: List<News>) {
        news.forEach { target ->
            target.isFavorite = _news.value?.any { it.diffId == target.diffId } ?: false
        }
    }
}