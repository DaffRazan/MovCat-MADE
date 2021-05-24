package com.daffa.moviecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    var id: Int,
    var backdrop_path: String,
    var genres: String,
    var overview: String,
    var poster_path: String,
    var release_date: String,
    var runtime: String,
    var title: String,
    var vote_average: Float,
    var isFavorite: Boolean = false
) : Parcelable
