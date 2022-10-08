package com.ramo.newsapp.domain.repository

import com.ramo.newsapp.domain.model.News

interface NewsRepository {
    suspend fun getNews(page: Int): List<News>
    suspend fun addFavorites(news: News)
    suspend fun deleteFavorites(news: News)
}