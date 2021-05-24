package com.daffa.moviecatalogue.core.data.source.remote.network

import com.daffa.moviecatalogue.BuildConfig
import com.daffa.moviecatalogue.core.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.core.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.daffa.moviecatalogue.core.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("discover/movie?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovies(): MovieResponse

    @GET("discover/tv?api_key=${BuildConfig.API_KEY}")
    suspend fun getTvShows(): TvShowResponse

    @GET("movie/{id}?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieById(
        @Path("id") id: Int,
    ): DetailMovieResponse

    @GET("tv/{id}?api_key=${BuildConfig.API_KEY}")
    suspend fun getTvShowById(
        @Path("id") id: Int,
    ): DetailTvShowResponse
}