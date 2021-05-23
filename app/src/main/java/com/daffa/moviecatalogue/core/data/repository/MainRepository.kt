package com.daffa.moviecatalogue.core.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.daffa.moviecatalogue.core.data.NetworkBoundResource
import com.daffa.moviecatalogue.core.data.source.Resource
import com.daffa.moviecatalogue.core.data.source.local.LocalDataSource
import com.daffa.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.daffa.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.daffa.moviecatalogue.core.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.core.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.core.domain.model.Movie
import com.daffa.moviecatalogue.core.domain.model.TvShow
import com.daffa.moviecatalogue.core.domain.repository.IMainRepository
import com.daffa.moviecatalogue.core.utils.DataMapper
import com.daffa.moviecatalogue.utils.AppExecutors

class MainRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMainRepository {
    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(
                    remoteData,
                    localDataSource,
                    appExecutors
                ).apply { instance = this }
            }
    }

    override fun getMovies(): LiveData<Resource<List<Movie>>> =
        object :
            NetworkBoundResource<List<Movie>, List<com.daffa.moviecatalogue.core.data.source.remote.response.model.Movie>>(
                appExecutors
            ) {
            override fun loadFromDb(): LiveData<List<Movie>> {
                return Transformations.map(localDataSource.getAllMovies()) {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<com.daffa.moviecatalogue.core.data.source.remote.response.model.Movie>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<com.daffa.moviecatalogue.core.data.source.remote.response.model.Movie>) {
                val movieList = DataMapper.mapMovieResponseToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()

    override fun getTvShows(): LiveData<Resource<List<TvShow>>> =
        object :
            NetworkBoundResource<List<TvShow>, List<com.daffa.moviecatalogue.core.data.source.remote.response.model.TvShow>>(
                appExecutors
            ) {
            override fun loadFromDb(): LiveData<List<TvShow>> {
                return Transformations.map(localDataSource.getAllTvShows()) {
                    DataMapper.mapTvShowEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<com.daffa.moviecatalogue.core.data.source.remote.response.model.TvShow>>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<com.daffa.moviecatalogue.core.data.source.remote.response.model.TvShow>) {
                val tvShowList = DataMapper.mapTvShowResponseToEntities(data)
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()

    override fun getMovieById(id: Int): LiveData<Resource<Movie>> =
        object :
            NetworkBoundResource<Movie, DetailMovieResponse>(appExecutors) {

            override fun loadFromDb(): LiveData<Movie> {
                return Transformations.map(localDataSource.getMovieById(id)) {
                    DataMapper.mapDetailMovieEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean =
                data != null && data.runtime == 0 && data.genres == ""

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getMovieById(id)

            override fun saveCallResult(data: DetailMovieResponse) {
                val detailMovie = DataMapper.mapDetailMovieResponseToEntity(data)
                localDataSource.updateMovie(detailMovie, false)
            }
        }.asLiveData()

    override fun getTvShowById(id: Int): LiveData<Resource<TvShow>> =
        object :
            NetworkBoundResource<TvShow, DetailTvShowResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<TvShow> {
                return Transformations.map(localDataSource.getTvShowById(id)) {
                    DataMapper.mapDetailTvShowEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: TvShow?): Boolean =
                data != null && data.runtime == "" && data.genres == ""

            override fun createCall(): LiveData<ApiResponse<DetailTvShowResponse>> =
                remoteDataSource.getTvShowById(id)


            override fun saveCallResult(data: DetailTvShowResponse) {
                val detailTvShow = DataMapper.mapDetailTvShowResponseToEntity(data)
                localDataSource.updateTvShow(detailTvShow,false)
            }
        }.asLiveData()

    override fun getFavoriteMovies(): LiveData<List<Movie>> {
        return Transformations.map(localDataSource.getFavoriteMovies()) {
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    override fun getFavoriteTvShows(): LiveData<List<TvShow>> {
        return Transformations.map(localDataSource.getFavoriteTvShow()) {
            DataMapper.mapTvShowEntityToDomain(it)
        }
    }

    override fun setFavoriteMovies(movie: Movie, state: Boolean) {
        val movieEnt = DataMapper.mapMovieDomainToEntities(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movieEnt, state)
        }
    }

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) {
        val tvShowEnt = DataMapper.mapTvShowDomainToEntities(tvShow)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShowEnt, state)
        }
    }

}