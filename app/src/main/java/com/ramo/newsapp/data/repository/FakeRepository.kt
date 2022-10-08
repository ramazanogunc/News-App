package com.ramo.newsapp.data.repository

import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.domain.repository.NewsRepository

class FakeRepository : NewsRepository {
    override suspend fun getNews(deviceId: String, page: Int): List<News> {
        return listOf(
            News(
                "Mükkemel Haber 1 ",
                "https://thumbs.dreamstime.com/b/elkos-men-174306777.jpg",
                "",
                "",
                "",
                false,
            ),
            News(
                "Mükkemel Haber 2 ",
                "https://thumbs.dreamstime.com/b/elkos-men-174306777.jpg",
                "",
                "",
                "",
                false,
            ),
            News(
                "Mükkemel Haber 3 ",
                "https://image.shutterstock.com/image-vector/news-anchor-on-tv-breaking-600w-664193623.jpg",
                "",
                "",
                "",
                false,
            )
        )
    }

    override suspend fun getFavorites(deviceId: String): MutableList<News> {
        TODO("Not yet implemented")
    }

    override suspend fun addFavorites(deviceId: String, news: News) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavorites(deviceId: String, news: News) {
        TODO("Not yet implemented")
    }


}