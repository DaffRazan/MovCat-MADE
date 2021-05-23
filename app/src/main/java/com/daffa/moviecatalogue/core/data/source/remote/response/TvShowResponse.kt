package com.daffa.moviecatalogue.core.data.source.remote.response

import com.daffa.moviecatalogue.core.data.source.remote.response.model.TvShow

data class TvShowResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<TvShow>
)