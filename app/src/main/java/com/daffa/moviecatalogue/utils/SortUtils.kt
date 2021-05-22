package com.daffa.moviecatalogue.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST_RELEASE = "Newest Release"
    const val OLDEST_RELEASE = "Oldest Release"
    const val BEST_VOTE = "Best Vote"
    const val WORST_VOTE = "Worst Vote"

    const val MOVIE_ENTITIES = "movie_entities"
    const val TV_SHOW_ENTITIES = "tv_show_entities"

    fun getSortedQuery(filter: String, table_name: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $table_name ")
        when (filter) {
            NEWEST_RELEASE -> simpleQuery.append("ORDER BY release_date DESC")
            OLDEST_RELEASE -> simpleQuery.append("ORDER BY release_date ASC")
            BEST_VOTE ->  simpleQuery.append("ORDER BY vote_average DESC")
            WORST_VOTE->  simpleQuery.append("ORDER BY vote_average ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}