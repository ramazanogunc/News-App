package com.ramo.newsapp.domain.usecase

import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.domain.repository.NewsRepository

class GetFavoritesUseCase(private val newsRepository: NewsRepository) :
    BaseUseCase<String, MutableList<News>>() {

    override suspend operator fun invoke(params: String): MutableList<News> {
        return newsRepository.getFavorites(params)
    }

}