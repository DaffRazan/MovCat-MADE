package com.daffa.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daffa.moviecatalogue.data.source.remote.network.ApiConfig
import com.daffa.moviecatalogue.data.source.remote.network.ApiResponse
import com.daffa.moviecatalogue.data.source.remote.network.ApiService
import com.daffa.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.data.source.remote.response.MovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.TvShowResponse
import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceFake (private val apiService: ApiService) :
    RemoteDataSourceInterface {

    override fun getMovies(): LiveData<ApiResponse<MovieResponse>> {
        val _movie = MutableLiveData<ApiResponse<MovieResponse>>()
        ApiConfig.getAPIService().getMovies()?.enqueue(enqueueCallback(_movie))
        return _movie
    }

    override fun getTvShows(): LiveData<ApiResponse<TvShowResponse>> {
        val _tvShow = MutableLiveData<ApiResponse<TvShowResponse>>()
        ApiConfig.getAPIService().getTvShows()?.enqueue(enqueueCallback(_tvShow))
        return _tvShow
    }

    override fun getMovieById(id: Int): LiveData<ApiResponse<DetailMovieResponse>> {
        val _movieId = MutableLiveData<ApiResponse<DetailMovieResponse>>()
        ApiConfig.getAPIService().getMovieById(id).enqueue(enqueueCallback(_movieId))
        return _movieId
    }

    override fun getTvShowById(id: Int): LiveData<ApiResponse<DetailTvShowResponse>> {
        val _tvShowId = MutableLiveData<ApiResponse<DetailTvShowResponse>>()
        ApiConfig.getAPIService().getTvShowById(id).enqueue(enqueueCallback(_tvShowId))
        return _tvShowId
    }

    private fun <T> enqueueCallback(mutableLiveData: MutableLiveData<ApiResponse<T>>): Callback<T?> {
        return object : Callback<T?> {
            override fun onResponse(call: Call<T?>, response: Response<T?>) {
                val data = response.body()
                mutableLiveData.postValue(
                    if (data != null) ApiResponse.Success(data) else ApiResponse.Empty(data)
                )
            }

            override fun onFailure(call: Call<T?>, t: Throwable) {
                mutableLiveData.value = ApiResponse.Error(t.message.toString())
            }

        }
    }
}