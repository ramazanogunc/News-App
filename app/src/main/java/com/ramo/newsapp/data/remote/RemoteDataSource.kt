package com.ramo.newsapp.data.remote

import com.ramo.newsapp.data.remote.response.TopHeadlinesResponse
import com.ramo.newsapp.domain.model.News

interface RemoteDataSource {

    suspend fun getNews(
        country: String, page: Int
    ): TopHeadlinesResponse

    suspend fun getFavorites(
        deviceId: String
    ): MutableList<News>

    suspend fun addFavorites(
        deviceId: String,
        news: News
    )

    suspend fun removeFavorites(
        deviceId: String,
        news: News
    )
}