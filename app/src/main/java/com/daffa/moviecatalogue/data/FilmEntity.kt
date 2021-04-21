package com.daffa.moviecatalogue.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmEntity (
    val id: String,
    val title: String,
    val imgPoster: Int,
    val description: String,
    val releaseDate: String,
    val genre: String,
    val score: String
) : Parcelable