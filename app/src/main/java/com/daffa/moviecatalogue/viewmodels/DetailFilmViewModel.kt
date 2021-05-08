package com.daffa.moviecatalogue.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.daffa.moviecatalogue.data.repository.MainRepository
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import javax.inject.Inject

class DetailFilmViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tvShow"
    }

     lateinit var detailMovie: LiveData<Resource<DetailMovieResponse>>
     lateinit var detailTvShow: LiveData<Resource<DetailTvShowResponse>>

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

    val getDetailMovie by lazy { detailMovie }
    val getDetailTvShow by lazy { detailTvShow }
}