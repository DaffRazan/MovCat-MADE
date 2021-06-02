package com.daffa.moviecatalogue.core.data.source.remote.response

import android.os.Parcelable
import com.daffa.moviecatalogue.core.data.source.remote.response.model.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    val results: List<Movie>
) : Parcelable
