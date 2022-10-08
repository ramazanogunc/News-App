package com.ramo.newsapp.domain.model

data class News(
    val title: String,
    val imageUrl: String,
    var isFavorite: Boolean
)
