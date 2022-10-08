package com.ramo.newsapp.data.remote

import com.ramo.newsapp.data.remote.response.TopHeadlinesResponse

class RemoteDataSourceImpl(
    private val newsService: NewsService
) : RemoteDataSource {

    override suspend fun getNews(country: String, page: Int): TopHeadlinesResponse {
        return newsService.getNews(country, page)
    }

}