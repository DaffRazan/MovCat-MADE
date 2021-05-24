package com.daffa.moviecatalogue.core.domain.repository

import com.daffa.moviecatalogue.core.domain.model.Movie
import com.daffa.moviecatalogue.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
    // get lists movies and tv shows
    fun getMovies() : Flow<com.daffa.moviecatalogue.core.data.source.Resource<List<Movie>>>
    fun getTvShows(): Flow<com.daffa.moviecatalogue.core.data.source.Resource<List<TvShow>>>

    // get movie and tv show detail
    fun getMovieById(id: Int): Flow<com.daffa.moviecatalogue.core.data.source.Resource<Movie>>
    fun getTvShowById(id: Int): Flow<com.daffa.moviecatalogue.core.data.source.Resource<TvShow>>

    // get com.daffa.moviecatalogue.favorite movies and tv shows
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun getFavoriteTvShows(): Flow<List<TvShow>>

    // set com.daffa.moviecatalogue.favorite movies and tv shows
    fun setFavoriteMovies(movie: Movie, state: Boolean)
    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)
}