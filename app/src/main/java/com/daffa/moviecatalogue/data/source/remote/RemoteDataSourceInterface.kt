package com.daffa.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.data.source.local.entity.TvShowEntity

interface DataSourceInterface {
    fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>
    fun getTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getMovieById(id: Int): LiveData<Resource<MovieEntity>>
    fun getTvShowById(id: Int): LiveData<Resource<TvShowEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>
    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteMovies(movie: MovieEntity, state: Boolean)
    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}