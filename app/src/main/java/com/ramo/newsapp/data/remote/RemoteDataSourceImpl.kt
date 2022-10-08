package com.ramo.newsapp.data.remote

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ramo.newsapp.data.remote.response.TopHeadlinesResponse
import com.ramo.newsapp.domain.model.News
import kotlinx.coroutines.tasks.await

class RemoteDataSourceImpl(
    private val newsService: NewsService
) : RemoteDataSource {

    override suspend fun getNews(country: String, page: Int): TopHeadlinesResponse {
        return newsService.getNews(country, page)
    }

    override suspend fun getFavorites(deviceId: String): MutableList<News> {
        val doc = getFirestoreRef(deviceId).get().await()
        return doc.toObjects(News::class.java).toMutableList()
    }

    override suspend fun addFavorites(deviceId: String, news: News) {
        getFirestoreRef(deviceId).document(news.title).set(news).await()
    }

    override suspend fun removeFavorites(deviceId: String, news: News) {
        getFirestoreRef(deviceId).document(news.title).delete().await()
    }

    private fun getFirestoreRef(deviceId: String) =
        Firebase.firestore.document("favorites/$deviceId")
            .collection("news")

}