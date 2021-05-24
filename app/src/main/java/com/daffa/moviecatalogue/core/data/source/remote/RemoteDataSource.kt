package com.daffa.moviecatalogue.core.data.source.remote

import android.util.Log
import com.daffa.moviecatalogue.core.data.source.remote.network.ApiConfig
import com.daffa.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.daffa.moviecatalogue.core.data.source.remote.network.ApiService
import com.daffa.moviecatalogue.core.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.core.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.daffa.moviecatalogue.core.data.source.remote.response.model.Movie
import com.daffa.moviecatalogue.core.data.source.remote.response.model.TvShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService).apply { instance = this }
            }
    }

    suspend fun getMovies(): Flow<ApiResponse<List<Movie>>> {
        //get data from remote api
        return flow {
            try {
                val response = ApiConfig.getAPIService().getMovies()
                val movieArray = response.results
                if (movieArray.isNotEmpty()) {
                    emit(ApiResponse.Success(movieArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShows(): Flow<ApiResponse<List<TvShow>>> {
        //get data from remote api
        return flow {
            try {
                val response = ApiConfig.getAPIService().getTvShows()
                val tvShowArray = response.results
                if (tvShowArray.isNotEmpty()) {
                    emit(ApiResponse.Success(tvShowArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieById(id: Int): Flow<ApiResponse<DetailMovieResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = ApiConfig.getAPIService().getMovieById(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShowById(id: Int): Flow<ApiResponse<DetailTvShowResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = ApiConfig.getAPIService().getTvShowById(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}