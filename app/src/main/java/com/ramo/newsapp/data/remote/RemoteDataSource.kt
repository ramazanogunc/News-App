package com.ramo.newsapp.data.remote

import com.ramo.newsapp.data.remote.response.TopHeadlinesResponse

interface RemoteDataSource {

    suspend fun getNews(
        country: String, page: Int
    ): TopHeadlinesResponse
}