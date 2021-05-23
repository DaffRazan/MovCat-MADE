package com.daffa.moviecatalogue.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.daffa.moviecatalogue.core.data.source.Resource
import com.daffa.moviecatalogue.core.domain.model.Movie
import com.daffa.moviecatalogue.core.domain.model.TvShow
import com.daffa.moviecatalogue.core.domain.usecase.MainUseCase
import javax.inject.Inject

class DetailFilmViewModel (private val mainUseCase: MainUseCase) :
    ViewModel() {

    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tvShow"
    }

    lateinit var detailMovie: LiveData<Resource<Movie>>
    lateinit var detailTvShow: LiveData<Resource<TvShow>>

    fun setFilm(id: String, category: String) {
        when (category) {
            MOVIE -> {
                detailMovie = mainUseCase.getMovieById(id.toInt())
            }
            TV_SHOW -> {
                detailTvShow = mainUseCase.getTvShowById(id.toInt())
            }
        }
    }

    fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        mainUseCase.setFavoriteMovies(movie, newState)
    }

    fun setFavoriteTvShow(tvShow: TvShow, newState: Boolean) {
        mainUseCase.setFavoriteTvShow(tvShow, newState)
    }

    val getDetailMovie by lazy { detailMovie }
    val getDetailTvShow by lazy { detailTvShow }
}