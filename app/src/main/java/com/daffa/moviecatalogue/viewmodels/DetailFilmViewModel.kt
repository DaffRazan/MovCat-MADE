package com.daffa.moviecatalogue.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daffa.moviecatalogue.data.repository.MainRepository
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.remote.network.ApiConfig
import com.daffa.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.data.source.remote.response.model.Movie
import com.daffa.moviecatalogue.data.source.remote.response.model.TvShow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DetailFilmViewModel @Inject constructor (private val mainRepository: MainRepository) :
    ViewModel() {
        companion object {
            const val MOVIE = "movie"
            const val TV_SHOW = "tvShow"
        }

        private lateinit var detailMovie: LiveData<DetailMovieResponse>
        private lateinit var detailTvShow: LiveData<DetailTvShowResponse>

        fun setFilm(id: String, category: String){
            when(category) {
                MOVIE -> {
                    detailMovie = mainRepository.getMovieById(id.toInt())
                }
                TV_SHOW -> {
                    detailTvShow = mainRepository.getTvShowById(id.toInt())
                }
            }
        }

        fun getDetailMovie() = detailMovie
        fun getDetailTvShow() = detailTvShow
}