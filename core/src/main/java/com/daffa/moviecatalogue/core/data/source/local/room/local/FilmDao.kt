package com.daffa.moviecatalogue.core.data.source.local.room.local

import androidx.room.*
import com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Query("SELECT * FROM movie_entities")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tv_show_entities")
    fun getTvShows(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM movie_entities WHERE id = :id")
    fun getMovieById(id: Int): Flow<MovieEntity>

    @Query("SELECT * FROM tv_show_entities WHERE id = :id")
    fun getTvShowById(id: Int): Flow<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM movie_entities WHERE isFavorite = 1")
    fun getFavMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tv_show_entities WHERE isFavorite = 1")
    fun getFavTvShows(): Flow<List<TvShowEntity>>
}