package com.ramo.newsapp.domain.usecase

import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.domain.repository.NewsRepository

class GetNewsUseCase(private val newsRepository: NewsRepository) :
    BaseUseCase<GetNewsUseCase.Params, List<News>>() {

    override suspend operator fun invoke(params: Params): List<News> {
        return newsRepository.getNews(params.deviceId, params.page)
    }

    data class Params(
        val deviceId: String,
        val page: Int
    )
}