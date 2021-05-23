package com.daffa.moviecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    var id: Int = 0,
    var adult: Boolean = false,
    var backdrop_path: String = "",
    var genres: String,
    var overview: String = "",
    var poster_path: String = "",
    var release_date: String = "",
    var runtime: Int,
    var title: String = "",
    var vote_average: Float = 0.0f,
    var isFavorite: Boolean = false
) : Parcelable
