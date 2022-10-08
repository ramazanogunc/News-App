package com.ramo.newsapp.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface ExampleService {

    @GET("posts")
    suspend fun exampleMethod(): Response<String>
}