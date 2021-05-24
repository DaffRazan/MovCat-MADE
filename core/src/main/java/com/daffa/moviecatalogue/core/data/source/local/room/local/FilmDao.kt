package com.daffa.moviecatalogue.core.data.source.local.room.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Query("SELECT * FROM movie_entities")
    fun getMovies(): Flow<List<com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity>>

    @Query("SELECT * FROM tv_show_entities")
    fun getTvShows(): Flow<List<com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity>>

    @Query("SELECT * FROM movie_entities WHERE id = :id")
    fun getMovieById(id: Int): Flow<com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity>

    @Query("SELECT * FROM tv_show_entities WHERE id = :id")
    fun getTvShowById(id: Int): Flow<com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity>)

    @Update
    fun updateMovie(movie: com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShows: List<com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity)

    @Query("SELECT * FROM movie_entities WHERE isFavorite = 1")
    fun getFavMovies(): Flow<List<com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity>>

    @Query("SELECT * FROM tv_show_entities WHERE isFavorite = 1")
    fun getFavTvShows(): Flow<List<com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity>>
}