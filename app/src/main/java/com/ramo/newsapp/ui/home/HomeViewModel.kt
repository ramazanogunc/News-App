package com.ramo.newsapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ramo.newsapp.core.BaseViewModel
import com.ramo.newsapp.core.ext.merge
import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : BaseViewModel() {

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> get() = _news

    var isLastPage: Boolean = false
    private var page = 1

    var deviceId: String = ""
        set(value) {
            val oldState = field
            field = value

            if (oldState.isBlank()) getNews()
        }

    private fun getNews() {
        safeScope {
            val news = getNewsUseCase(GetNewsUseCase.Params(deviceId, page))
            _news.value = _news.value?.merge(news) ?: news
            if (news.size < 20) // max page item size 20
                isLastPage = true
        }
    }

    fun nextNews() {
        if (isLastPage) return
        page++
        getNews()
    }
}