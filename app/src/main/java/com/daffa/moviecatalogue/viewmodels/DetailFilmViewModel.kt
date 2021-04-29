package com.daffa.moviecatalogue.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.daffa.moviecatalogue.data.repository.MainRepository
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import javax.inject.Inject

class DetailFilmViewModel @Inject constructor(private val repository: MainRepository) :
    ViewModel() {
    private lateinit var dataExtra: MutableList<Int>

    companion object {
        const val DATA_DESTINATION = 0
        const val DATA_ID = 1
    }

    var dataMovie: LiveData<Resource<DetailMovieResponse>>? = null
    var dataTvShow: LiveData<Resource<DetailTvShowResponse>>? = null

    fun getDataExtra(data: Int) = this.dataExtra[data]

    fun setDataExtra(dataDesc: Int, dataId: Int) {
        dataExtra = mutableListOf(dataDesc, dataId)

        if (dataMovie == null) dataMovie = repository.getMovieById(dataExtra[DATA_ID])
        if (dataTvShow == null) dataTvShow = repository.getTvShowById(dataExtra[DATA_ID])
    }
}