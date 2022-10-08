package com.ramo.newsapp.domain.model

import android.os.Parcelable
import com.ramo.sweetrecycler.SweetDiff
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String,
    val imageUrl: String,
    var isFavorite: Boolean
) : Parcelable, SweetDiff {

    override val diffId: String
        get() = title

}
