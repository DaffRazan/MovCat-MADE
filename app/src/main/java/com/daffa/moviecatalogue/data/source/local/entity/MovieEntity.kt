package com.daffa.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movie_entities")
@Parcelize
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "adult")
    var adult: Boolean = false,

    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String = "",

    @ColumnInfo(name = "genres")
    var genres: String,

    @ColumnInfo(name = "overview")
    var overview: String = "",

    @ColumnInfo(name = "poster_path")
    var poster_path: String = "",

    @ColumnInfo(name = "release_date")
    var release_date: String = "",

    @ColumnInfo(name = "runtime")
    var runtime: Int,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "vote_average")
    var vote_average: Float = 0.0f,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable