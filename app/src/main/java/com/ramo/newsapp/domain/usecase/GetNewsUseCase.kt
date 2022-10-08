package com.ramo.newsapp.domain.usecase

import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.domain.repository.NewsRepository

class GetNewsUseCase(private val newsRepository: NewsRepository) : BaseUseCase<Int, List<News>>() {

    override suspend operator fun invoke(params: Int): List<News> {
        return newsRepository.getNews(params)
    }

}