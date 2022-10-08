package com.ramo.newsapp.data.remote.response

import com.google.gson.annotations.SerializedName
import com.ramo.newsapp.domain.model.News

data class TopHeadlinesResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem?>? = null,

    @field:SerializedName("status")
    val status: String? = null
) {

    fun toNews(): List<News> {
        val list = mutableListOf<News>()
        articles?.forEach { if (it != null) list.add(it.toNews()) }
        return list.toList()
    }
}

data class ArticlesItem(

    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("source")
    val source: Source? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("content")
    val content: String? = null
) {

    fun toNews(): News = News(
        title = title ?: "",
        description = description ?: "",
        imageUrl = urlToImage ?: "",
        url = url ?: "",
        content = content ?: "",
        isFavorite = false
    )
}

data class Source(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null
)
