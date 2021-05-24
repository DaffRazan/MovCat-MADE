package com.daffa.moviecatalogue.core.data.repository

import com.daffa.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.daffa.moviecatalogue.core.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.core.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.core.domain.model.Movie
import com.daffa.moviecatalogue.core.domain.model.TvShow
import com.daffa.moviecatalogue.core.domain.repository.IMainRepository
import com.daffa.moviecatalogue.core.utils.AppExecutors
import com.daffa.moviecatalogue.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainRepository(
    private val remoteDataSource: com.daffa.moviecatalogue.core.data.source.remote.RemoteDataSource,
    private val localDataSource: com.daffa.moviecatalogue.core.data.source.local.LocalDataSource,
    private val executorsApp: AppExecutors
) : IMainRepository {

    override fun getMovies(): Flow<com.daffa.moviecatalogue.core.data.source.Resource<List<Movie>>> =
        object :
            com.daffa.moviecatalogue.core.data.NetworkBoundResource<List<Movie>, List<com.daffa.moviecatalogue.core.data.source.remote.response.model.Movie>>(
                executorsApp
            ) {
            override fun loadFromDb(): Flow<List<Movie>> {
                return localDataSource.getAllMovies()
                    .map { DataMapper.mapMovieEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<com.daffa.moviecatalogue.core.data.source.remote.response.model.Movie>>> =
                remoteDataSource.getMovies()

            override suspend fun saveCallResult(data: List<com.daffa.moviecatalogue.core.data.source.remote.response.model.Movie>) {
                val movieList = DataMapper.mapMovieResponseToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun getTvShows(): Flow<com.daffa.moviecatalogue.core.data.source.Resource<List<TvShow>>> =
        object :
            com.daffa.moviecatalogue.core.data.NetworkBoundResource<List<TvShow>, List<com.daffa.moviecatalogue.core.data.source.remote.response.model.TvShow>>(
                executorsApp
            ) {
            override fun loadFromDb(): Flow<List<TvShow>> {
                return localDataSource.getAllTvShows()
                    .map { DataMapper.mapTvShowEntityToDomain(it) }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<com.daffa.moviecatalogue.core.data.source.remote.response.model.TvShow>>> =
                remoteDataSource.getTvShows()

            override suspend fun saveCallResult(data: List<com.daffa.moviecatalogue.core.data.source.remote.response.model.TvShow>) {
                val tvShowList = DataMapper.mapTvShowResponseToEntities(data)
                localDataSource.insertTvShows(tvShowList)
            }
        }.asFlow()

    override fun getMovieById(id: Int): Flow<com.daffa.moviecatalogue.core.data.source.Resource<Movie>> =
        object :
            com.daffa.moviecatalogue.core.data.NetworkBoundResource<Movie, DetailMovieResponse>(executorsApp) {

            override fun loadFromDb(): Flow<Movie> {
                return localDataSource.getMovieById(id)
                    .map { DataMapper.mapDetailMovieEntityToDomain(it) }
            }

            override fun shouldFetch(data: Movie?): Boolean =
                data != null && data.runtime == 0 && data.genres == ""

            override suspend fun createCall(): Flow<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getMovieById(id)

            override suspend fun saveCallResult(data: DetailMovieResponse) {
                val detailMovie = DataMapper.mapDetailMovieResponseToEntity(data)
                localDataSource.updateMovie(detailMovie, false)
            }
        }.asFlow()

    override fun getTvShowById(id: Int): Flow<com.daffa.moviecatalogue.core.data.source.Resource<TvShow>> =
        object :
            com.daffa.moviecatalogue.core.data.NetworkBoundResource<TvShow, DetailTvShowResponse>(executorsApp) {
            override fun loadFromDb(): Flow<TvShow> {
                return localDataSource.getTvShowById(id)
                    .map { DataMapper.mapDetailTvShowEntityToDomain(it) }
            }

            override fun shouldFetch(data: TvShow?): Boolean =
                data != null && data.runtime == "" && data.genres == ""

            override suspend fun createCall(): Flow<ApiResponse<DetailTvShowResponse>> =
                remoteDataSource.getTvShowById(id)

            override suspend fun saveCallResult(data: DetailTvShowResponse) {
                val detailTvShow = DataMapper.mapDetailTvShowResponseToEntity(data)
                localDataSource.updateTvShow(detailTvShow, false)
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map { DataMapper.mapMovieEntitiesToDomain(it) }
    }

    override fun getFavoriteTvShows(): Flow<List<TvShow>> {
        return localDataSource.getFavoriteTvShow().map { DataMapper.mapTvShowEntityToDomain(it) }
    }

    override fun setFavoriteMovies(movie: Movie, state: Boolean) {
        val movieEnt = DataMapper.mapMovieDomainToEntities(movie)
        executorsApp.diskIO().execute {
            localDataSource.setFavoriteMovie(movieEnt, state)
        }
    }

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) {
        val tvShowEnt = DataMapper.mapTvShowDomainToEntities(tvShow)
        executorsApp.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShowEnt, state)
        }
    }

}