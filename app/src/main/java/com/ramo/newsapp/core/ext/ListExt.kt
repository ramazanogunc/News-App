package com.ramo.newsapp.core.ext

fun <T> List<T>.merge(list: List<T>): List<T> {
    val mutable = this.toMutableList()
    mutable.addAll(list)
    return mutable.toList()
}