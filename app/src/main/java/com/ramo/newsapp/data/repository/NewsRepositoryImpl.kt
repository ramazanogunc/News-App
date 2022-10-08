package com.ramo.newsapp.data.repository

import com.ramo.newsapp.data.Repository
import com.ramo.newsapp.data.remote.RemoteDataSource
import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository(), NewsRepository {

    override suspend fun getNews(page: Int): List<News> {
        val data = remoteDataSource.getNews("us", page)
        return data.toNews()
    }

    override suspend fun addFavorites(news: News) {
        news.isFavorite = true
    }

    override suspend fun deleteFavorites(news: News) {
        news.isFavorite = false

    }
}