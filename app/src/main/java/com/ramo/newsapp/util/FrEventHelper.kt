package com.ramo.newsapp.util

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.ramo.newsapp.domain.model.News

object FrEventHelper {

    private var frAnalytics: FirebaseAnalytics? = null

    fun setContext(context: Context) {
        frAnalytics = FirebaseAnalytics.getInstance(context)
    }

    fun addToFavorites(news: News) = event(
        EVENT_ADD_TO_FAVORITES, mapOf(
            Pair("title", news.title),
            Pair("description", news.description),
            Pair("content", news.content),
            Pair("url", news.url),
        )
    )

    fun removeFromFavorites(news: News) = event(
        EVENT_REMOVE_FROM_FAVORITES, mapOf(
            Pair("title", news.title),
            Pair("description", news.description),
            Pair("content", news.content),
            Pair("url", news.url),
        )
    )

    private fun event(name: String, map: Map<String, Any?>) {
        val bundle = mapToBundle(map)
        frAnalytics?.logEvent(name, bundle)
    }

    private fun mapToBundle(map: Map<String, Any?>): Bundle = Bundle().apply {
        map.forEach {
            when (val value = it.value) {
                is String -> putString(it.key, value)
                is Int -> putInt(it.key, value)
                is Long -> putLong(it.key, value)
                is Float -> putFloat(it.key, value)
                is Double -> putDouble(it.key, value)
                is Map<*, *> -> {
                    try {
                        putBundle(it.key, mapToBundle(value as Map<String, Any?>))
                    } catch (e: Exception) {
                        Log.e("TAG", "Nested event parameter problem.")
                    }
                }
            }
        }
    }

    private const val EVENT_ADD_TO_FAVORITES = "add_to_favorites"
    private const val EVENT_REMOVE_FROM_FAVORITES = "remove_from_favorites"

}