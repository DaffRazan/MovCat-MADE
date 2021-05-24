package com.daffa.moviecatalogue.core.data.source.local

import com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity
import com.daffa.moviecatalogue.core.data.source.local.room.local.FilmDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDataSource private constructor(private val mFilmDao: FilmDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getAllMovies(): Flow<List<MovieEntity>> =
        mFilmDao.getMovies()

    fun getAllTvShows(): Flow<List<TvShowEntity>> =
        mFilmDao.getTvShows()

    fun getMovieById(id: Int): Flow<MovieEntity> = mFilmDao.getMovieById(id)

    fun getTvShowById(id: Int): Flow<TvShowEntity> = mFilmDao.getTvShowById(id)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mFilmDao.updateMovie(movie)
    }

    suspend fun updateMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        withContext(Dispatchers.IO) {
            mFilmDao.updateMovie(movie)
        }
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mFilmDao.updateTvShow(tvShow)
    }

    suspend fun updateTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        withContext(Dispatchers.IO) {
            mFilmDao.updateTvShow(tvShow)
        }
    }

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = mFilmDao.getFavMovies()

    fun getFavoriteTvShow(): Flow<List<TvShowEntity>> = mFilmDao.getFavTvShows()

    suspend fun insertMovies(movies: List<MovieEntity>) = mFilmDao.insertMovies(movies)

    suspend fun insertTvShows(tvShows: List<TvShowEntity>) = mFilmDao.insertTvShows(tvShows)
}