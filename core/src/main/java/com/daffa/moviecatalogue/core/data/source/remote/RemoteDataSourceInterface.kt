package com.daffa.moviecatalogue.core.data.source.remote

import androidx.lifecycle.LiveData

interface DataSourceInterface {
    fun getMovies(): LiveData<com.daffa.moviecatalogue.core.data.source.Resource<com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity>>
    fun getTvShows(): LiveData<com.daffa.moviecatalogue.core.data.source.Resource<com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity>>
    fun getMovieById(id: Int): LiveData<com.daffa.moviecatalogue.core.data.source.Resource<com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity>>
    fun getTvShowById(id: Int): LiveData<com.daffa.moviecatalogue.core.data.source.Resource<com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity>>

    fun getFavoriteMovies(): LiveData<com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity>
    fun getFavoriteTvShows(): LiveData<com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity>

    fun setFavoriteMovies(movie: com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity, state: Boolean)
    fun setFavoriteTvShow(tvShow: com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity, state: Boolean)
}