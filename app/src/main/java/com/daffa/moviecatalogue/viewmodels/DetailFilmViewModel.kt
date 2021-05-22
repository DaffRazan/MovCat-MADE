package com.daffa.moviecatalogue.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.daffa.moviecatalogue.data.repository.MainRepository
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.data.source.local.entity.TvShowEntity
import javax.inject.Inject

class DetailFilmViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tvShow"
    }

    lateinit var detailMovie: LiveData<Resource<MovieEntity>>
    lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>

    fun setFilm(id: String, category: String) {
        when (category) {
            MOVIE -> {
                detailMovie = mainRepository.getMovieById(id.toInt())
            }
            TV_SHOW -> {
                detailTvShow = mainRepository.getTvShowById(id.toInt())
            }
        }
    }

    fun setFavoriteMovie() {
        val detailMovieValue = detailMovie.value

        if (detailMovieValue?.data != null) {
            val newState = !detailMovieValue.data.isFavorite
            mainRepository.setFavoriteMovies(detailMovieValue.data, newState)
        }
    }

    fun setFavoriteTvShow() {
        val detailTvShowValue = detailTvShow.value

        if (detailTvShowValue?.data != null) {
            val newState = !detailTvShowValue.data.isFavorite
            mainRepository.setFavoriteTvShow(detailTvShowValue.data, newState)
        }
    }

    val getDetailMovie by lazy { detailMovie }
    val getDetailTvShow by lazy { detailTvShow }
}