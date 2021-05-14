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
        val resource = detailMovie.value

        if (resource?.data != null) {
            val newState = !resource.data.isFav
            mainRepository.setFavoriteMovies(resource.data, newState)
        }
    }

    fun setFavoriteTvShow() {
        val resource = detailTvShow.value

        if (resource?.data != null) {
            val newState = !resource.data.isFav
            mainRepository.setFavoriteTvShow(resource.data, newState)
        }
    }

    val getDetailMovie by lazy { detailMovie }
    val getDetailTvShow by lazy { detailTvShow }
}