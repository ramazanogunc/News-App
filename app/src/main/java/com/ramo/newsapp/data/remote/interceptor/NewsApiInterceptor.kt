package com.ramo.newsapp.data.remote.interceptor

import com.ramo.newsapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NewsApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter("apiKey", BuildConfig.NEWS_API_KEY)
            .build()
        return chain.proceed(chain.request().newBuilder().url(url).build())
    }
}