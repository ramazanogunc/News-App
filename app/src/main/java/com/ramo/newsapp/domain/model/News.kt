package com.ramo.newsapp.domain.model

import android.os.Parcelable
import com.ramo.sweetrecycler.SweetDiff
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    var title: String = "",
    var description: String = "",
    var content: String = "",
    var imageUrl: String = "",
    var url: String = "",
    var isFavorite: Boolean = false,
) : Parcelable, SweetDiff {

    override val diffId: String
        get() = title

}
