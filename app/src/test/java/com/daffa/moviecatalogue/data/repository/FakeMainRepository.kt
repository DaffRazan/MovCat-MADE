package com.daffa.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.remote.RemoteDataSource
import com.daffa.moviecatalogue.data.source.remote.network.ApiResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.data.source.remote.response.MovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.TvShowResponse

class FakeMainRepository(private val remoteDataSource: RemoteDataSource) {

    fun getMovies(): LiveData<Resource<MovieResponse>> {
        return result(
            MediatorLiveData<Resource<MovieResponse>>(),
            remoteDataSource.getMovies()
        )
    }

    fun getTvShows(): LiveData<Resource<TvShowResponse>> {
        return result(
            MediatorLiveData<Resource<TvShowResponse>>(),
            remoteDataSource.getTvShows()
        )
    }

    fun getMovieById(id: Int): LiveData<Resource<DetailMovieResponse>> {
        return result(
            MediatorLiveData<Resource<DetailMovieResponse>>(),
            remoteDataSource.getMovieById(id)
        )
    }

    fun getTvShowById(id: Int): LiveData<Resource<DetailTvShowResponse>> {
        return result(
            MediatorLiveData<Resource<DetailTvShowResponse>>(),
            remoteDataSource.getTvShowById(id)
        )
    }

    private fun <T> result(
        result: MediatorLiveData<Resource<T>>, response: LiveData<ApiResponse<T>>
    ): LiveData<Resource<T>> {
        result.postValue(Resource.Loading())
        result.addSource(response) {
            result.removeSource(response)

            when (it) {
                is ApiResponse.Success -> result.postValue(Resource.Success(it.data))
                is ApiResponse.Error -> result.postValue(Resource.Error(it.errorMessage))
                is ApiResponse.Empty -> result.postValue(Resource.Empty(it.data))
            }
        }
        return result
    }
}