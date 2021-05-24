package com.daffa.moviecatalogue.core.data.source.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDataSource(private val mFilmDao: com.daffa.moviecatalogue.core.data.source.local.room.local.FilmDao) {
    fun getAllMovies(): Flow<List<com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity>> =
        mFilmDao.getMovies()

    fun getAllTvShows(): Flow<List<com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity>> =
        mFilmDao.getTvShows()

    fun getMovieById(id: Int): Flow<com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity> = mFilmDao.getMovieById(id)

    fun getTvShowById(id: Int): Flow<com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity> = mFilmDao.getTvShowById(id)

    fun setFavoriteMovie(movie: com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mFilmDao.updateMovie(movie)
    }

    suspend fun updateMovie(movie: com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        withContext(Dispatchers.IO) {
            mFilmDao.updateMovie(movie)
        }
    }

    fun setFavoriteTvShow(tvShow: com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mFilmDao.updateTvShow(tvShow)
    }

    suspend fun updateTvShow(tvShow: com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        withContext(Dispatchers.IO) {
            mFilmDao.updateTvShow(tvShow)
        }
    }

    fun getFavoriteMovies(): Flow<List<com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity>> = mFilmDao.getFavMovies()

    fun getFavoriteTvShow(): Flow<List<com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity>> = mFilmDao.getFavTvShows()

    suspend fun insertMovies(movies: List<com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity>) = mFilmDao.insertMovies(movies)

    suspend fun insertTvShows(tvShows: List<com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity>) = mFilmDao.insertTvShows(tvShows)
}