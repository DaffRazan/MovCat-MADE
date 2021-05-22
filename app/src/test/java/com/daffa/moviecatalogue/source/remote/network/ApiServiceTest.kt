package com.daffa.moviecatalogue.source.remote.network

import com.daffa.moviecatalogue.data.source.remote.network.ApiConfig
import com.daffa.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.data.source.remote.response.MovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.TvShowResponse
import com.daffa.moviecatalogue.utils.Constants.TIME_OUT
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class ApiServiceTest {
    private val apiService = ApiConfig.getAPIService()
    private val countDownLatch = CountDownLatch(1)

    // Uji API

    @Test
    fun getMoviesApi() {
        apiService.getMovies()?.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                countDownLatch.countDown()
                print(response.body()?.results)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                countDownLatch.countDown()
                t.printStackTrace()
            }

        })
        countDownLatch.await(TIME_OUT, TimeUnit.SECONDS)
    }

    @Test
    fun getMovieByIdApi() {
        apiService.getMovieById(615457).enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>, movieResponse: Response<DetailMovieResponse>
            ) {
                countDownLatch.countDown()
                print(movieResponse.body())
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        countDownLatch.await(TIME_OUT, TimeUnit.SECONDS)
    }

    @Test
    fun getTvShowsApi() {
        apiService.getTvShows()?.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                countDownLatch.countDown()
                print(response.body()?.results)
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        countDownLatch.await(TIME_OUT, TimeUnit.SECONDS)
    }

    @Test
    fun getTvShowByIdApi() {
        apiService.getTvShowById(60735).enqueue(object : Callback<DetailTvShowResponse> {
            override fun onResponse(
                call: Call<DetailTvShowResponse>, tvShowResponse: Response<DetailTvShowResponse>
            ) {
                countDownLatch.countDown()
                print(tvShowResponse.body())
            }

            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        countDownLatch.await(TIME_OUT, TimeUnit.SECONDS)
    }
}