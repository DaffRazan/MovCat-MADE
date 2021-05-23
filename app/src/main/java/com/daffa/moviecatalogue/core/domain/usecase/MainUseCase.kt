package com.daffa.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import com.daffa.moviecatalogue.core.data.source.Resource
import com.daffa.moviecatalogue.core.domain.model.Movie
import com.daffa.moviecatalogue.core.domain.model.TvShow

interface MainUseCase {
    // get lists movies and tv shows
    fun getMovies() : LiveData<Resource<List<Movie>>>
    fun getTvShows(): LiveData<Resource<List<TvShow>>>

    // get movie and tv show detail
    fun getMovieById(id: Int): LiveData<Resource<Movie>>
    fun getTvShowById(id: Int): LiveData<Resource<TvShow>>

    // get favorite movies and tv shows
    fun getFavoriteMovies(): LiveData<List<Movie>>
    fun getFavoriteTvShows(): LiveData<List<TvShow>>

    // set favorite movies and tv shows
    fun setFavoriteMovies(movie: Movie, state: Boolean)
    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)
}