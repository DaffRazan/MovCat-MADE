package com.daffa.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.daffa.moviecatalogue.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.data.source.local.entity.TvShowEntity
import com.daffa.moviecatalogue.data.source.local.room.local.FilmDao
import com.daffa.moviecatalogue.utils.SortUtils
import com.daffa.moviecatalogue.utils.SortUtils.MOVIE_ENTITIES
import com.daffa.moviecatalogue.utils.SortUtils.TV_SHOW_ENTITIES

class LocalDataSource(private val mFilmDao: FilmDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getAllMovies(sort: String): DataSource.Factory<Int, MovieEntity> =
        mFilmDao.getMovies(SortUtils.getSortedQuery(sort, MOVIE_ENTITIES))

    fun getAllTvShows(sort: String): DataSource.Factory<Int, TvShowEntity> =
        mFilmDao.getTvShows(SortUtils.getSortedQuery(sort, TV_SHOW_ENTITIES))

    fun getMovieById(id: Int): LiveData<MovieEntity> = mFilmDao.getMovieById(id)

    fun getTvShowById(id: Int): LiveData<TvShowEntity> = mFilmDao.getTvShowById(id)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mFilmDao.updateMovie(movie)
    }

    fun updateMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mFilmDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mFilmDao.updateTvShow(tvShow)
    }

    fun updateTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mFilmDao.updateTvShow(tvShow)
    }

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = mFilmDao.getFavMovies()

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> = mFilmDao.getFavTvShows()

    fun insertMovies(movies: List<MovieEntity>) = mFilmDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShowEntity>) = mFilmDao.insertTvShows(tvShows)
}