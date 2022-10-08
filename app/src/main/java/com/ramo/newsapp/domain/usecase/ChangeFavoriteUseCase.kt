package com.ramo.newsapp.domain.usecase

import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.domain.repository.NewsRepository

class ChangeFavoriteUseCase(private val newsRepository: NewsRepository) :
    BaseUseCase<ChangeFavoriteUseCase.Params, Unit>() {

    override suspend operator fun invoke(params: Params) {
        if (params.news.isFavorite.not())
            newsRepository.addFavorites(params.deviceId, params.news)
        else
            newsRepository.deleteFavorites(params.deviceId, params.news)
    }

    data class Params(
        val deviceId: String,
        val news: News
    )
}