package com.ramo.newsapp.domain.repository

import com.ramo.newsapp.domain.model.News

interface NewsRepository {
    suspend fun getNews(deviceId: String, page: Int): List<News>
    suspend fun getFavorites(deviceId: String): MutableList<News>
    suspend fun addFavorites(deviceId: String, news: News)
    suspend fun deleteFavorites(deviceId: String, news: News)
}