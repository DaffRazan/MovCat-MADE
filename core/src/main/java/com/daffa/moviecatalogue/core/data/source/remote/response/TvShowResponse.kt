package com.daffa.moviecatalogue.core.data.source.remote.response

import androidx.annotation.Keep
import com.daffa.moviecatalogue.core.data.source.remote.response.model.TvShow

@Keep
data class TvShowResponse(
    val results: List<TvShow>
)