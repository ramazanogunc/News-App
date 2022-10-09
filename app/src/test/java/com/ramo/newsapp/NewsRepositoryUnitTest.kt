package com.ramo.newsapp

import com.ramo.newsapp.data.remote.RemoteDataSource
import com.ramo.newsapp.data.remote.RemoteDataSourceImpl
import com.ramo.newsapp.data.remote.response.ArticlesItem
import com.ramo.newsapp.data.remote.response.TopHeadlinesResponse
import com.ramo.newsapp.data.repository.NewsRepositoryImpl
import com.ramo.newsapp.domain.model.News
import com.ramo.newsapp.domain.repository.NewsRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class NewsRepositoryUnitTest {

    private val testDeviceId = "unit_test_device_id"
    private val remoteDataSource: RemoteDataSource = mock(RemoteDataSourceImpl::class.java)
    private val repository: NewsRepository = NewsRepositoryImpl(remoteDataSource)
    private val response = TopHeadlinesResponse(
        articles = listOf(
            ArticlesItem(
                title = "test1",
                description = "test1",
                content = "test1",
                urlToImage = "test1",
                url = "test1",
            ), ArticlesItem(
                title = "test2",
                description = "test2",
                content = "test2",
                urlToImage = "test2",
                url = "test2",
            )
        )
    )
    private val mockNews = response.toNews().apply {
        get(1).isFavorite = true
    }
    private val mockFavs = mutableListOf(
        News("test2", "test2", "test2", "test2", "test2", true)
    )


    @Test
    fun `getnews sholud return news list without favorites`() = runTest {

        `when`(remoteDataSource.getNews("us", 1)).thenReturn(response)
        `when`(remoteDataSource.getFavorites(testDeviceId)).thenReturn(mutableListOf())
        val actually = repository.getNews(testDeviceId, 1)

        val expected = response.toNews()
        assertEquals(expected, actually)
    }

    @Test
    fun `getnews sholud return news list with favorites`() = runTest {
        `when`(remoteDataSource.getNews("us", 1)).thenReturn(response)
        `when`(remoteDataSource.getFavorites(testDeviceId)).thenReturn(mockFavs)

        val actually = repository.getNews(testDeviceId, 1)

        assertEquals(mockNews, actually)
    }

    @Test
    fun `getFavorites should return favorite news list`() = runTest {
        `when`(remoteDataSource.getFavorites(testDeviceId)).thenReturn(mockFavs)

        val actually = repository.getFavorites(testDeviceId)

        assertEquals(mockFavs, actually)
    }

    @Test
    fun `addFavorites should change isFavorite status true`() = runTest {
        val news = mockNews.last()
        repository.addFavorites(testDeviceId, news)

        assertEquals(true, news.isFavorite)
    }

    @Test
    fun `deleteFavorites should change isFavorite status false`() = runTest {
        val news = mockFavs.first()
        repository.deleteFavorites(testDeviceId, news)

        assertEquals(false, news.isFavorite)
    }
}