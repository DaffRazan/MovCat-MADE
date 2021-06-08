package com.daffa.moviecatalogue.core.data.source.remote.response

import android.os.Parcelable
import androidx.annotation.Keep
import com.daffa.moviecatalogue.core.data.source.remote.response.model.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class MovieResponse(
    val results: List<Movie>
) : Parcelable
