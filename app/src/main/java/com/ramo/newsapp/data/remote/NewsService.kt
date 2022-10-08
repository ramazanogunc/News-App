package com.ramo.newsapp.data.remote

import com.ramo.newsapp.data.remote.response.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("page") page: Int
    ): TopHeadlinesResponse

}