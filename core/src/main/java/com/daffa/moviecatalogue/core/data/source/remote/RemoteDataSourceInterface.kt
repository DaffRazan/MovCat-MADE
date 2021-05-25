package com.daffa.moviecatalogue.core.data.source.remote

import androidx.lifecycle.LiveData
import com.daffa.moviecatalogue.core.data.source.Resource
import com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity

interface DataSourceInterface {
    fun getMovies(): LiveData<Resource<MovieEntity>>
    fun getTvShows(): LiveData<Resource<TvShowEntity>>
    fun getMovieById(id: Int): LiveData<Resource<MovieEntity>>
    fun getTvShowById(id: Int): LiveData<Resource<TvShowEntity>>

    fun getFavoriteMovies(): LiveData<MovieEntity>
    fun getFavoriteTvShows(): LiveData<TvShowEntity>

    fun setFavoriteMovies(movie: MovieEntity, state: Boolean)
    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}