package com.ramo.newsapp.data.repository

import com.ramo.newsapp.data.Repository
import com.ramo.newsapp.data.remote.RemoteDataSource
import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.domain.repository.NewsRepository
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : Repository(), NewsRepository {

    override suspend fun getNews(deviceId: String, page: Int): List<News> {
        return exec {
            val data = remoteDataSource.getNews("us", page)
            val favs = remoteDataSource.getFavorites(deviceId)
            val list = data.toNews()
            list.forEach { realNews ->
                realNews.isFavorite =
                    favs.any { realNews.diffId == it.diffId }
            }
            list
        }
    }

    override suspend fun getFavorites(deviceId: String): MutableList<News> {
        return exec {
            remoteDataSource.getFavorites(deviceId)
        }
    }

    override suspend fun addFavorites(deviceId: String, news: News) {
        return exec {
            news.isFavorite = true
            remoteDataSource.addFavorites(deviceId, news)
        }
    }

    override suspend fun deleteFavorites(deviceId: String, news: News) {
        return exec {
            news.isFavorite = false
            remoteDataSource.removeFavorites(deviceId, news)
        }
    }
}