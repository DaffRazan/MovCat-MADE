package com.daffa.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.daffa.moviecatalogue.data.NetworkBoundResource
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.local.LocalDataSource
import com.daffa.moviecatalogue.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.data.source.local.entity.TvShowEntity
import com.daffa.moviecatalogue.data.source.remote.DataSourceInterface
import com.daffa.moviecatalogue.data.source.remote.RemoteDataSource
import com.daffa.moviecatalogue.data.source.remote.network.ApiResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.data.source.remote.response.MovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.TvShowResponse
import com.daffa.moviecatalogue.data.source.remote.response.model.Movie
import com.daffa.moviecatalogue.data.source.remote.response.model.TvShow
import com.daffa.moviecatalogue.utils.AppExecutors

class FakeMainRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : DataSourceInterface {

    override fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<Movie>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllMovies(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<MovieEntity>()

                for (response in data) {
                    val movie = MovieEntity(
                        id = response.id,
                        backdrop_path = response.backdrop_path,
                        genres = "",
                        overview = response.overview,
                        poster_path = response.poster_path,
                        release_date = response.release_date,
                        runtime = 0,
                        title = response.title,
                        vote_average = response.vote_average,
                        isFav = false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShow>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllTvShows(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> =
                remoteDataSource.getTvShows()


            override fun saveCallResult(data: List<TvShow>) {
                val tvShowList = ArrayList<TvShowEntity>()

                for (response in data) {
                    val tvShow = TvShowEntity(
                        id = response.id,
                        backdrop_path = response.backdrop_path,
                        genres = "",
                        overview = response.overview,
                        poster_path = response.poster_path,
                        release_date = response.first_air_date,
                        runtime = "",
                        title = response.name,
                        vote_average = response.vote_average,
                        isFav = false
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getMovieById(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, DetailMovieResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<MovieEntity> = localDataSource.getMovieById(id)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data != null && data.runtime == 0 && data.genres == ""

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getMovieById(id)


            override fun saveCallResult(data: DetailMovieResponse) {
                val genreBuilder = StringBuilder()

                val iterator = data.genres.iterator()
                while (iterator.hasNext()) {
                    val obj = iterator.next()
                    if (iterator.hasNext()) {
                        genreBuilder.append(obj.name)
                        genreBuilder.append(", ")
                    } else {
                        genreBuilder.append(obj.name)
                    }
                }

                val movieDetail = MovieEntity(
                    id = data.id,
                    backdrop_path = data.backdrop_path,
                    genres = genreBuilder.toString(),
                    overview = data.overview,
                    poster_path = data.poster_path,
                    release_date = data.release_date,
                    runtime = data.runtime,
                    title = data.title,
                    vote_average = data.vote_average,
                    isFav = false
                )
                localDataSource.updateMovie(movieDetail, false)
            }
        }.asLiveData()
    }

    override fun getTvShowById(id: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, DetailTvShowResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<TvShowEntity> =
                localDataSource.getTvShowById(id)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data != null && data.runtime == "" && data.genres == ""

            override fun createCall(): LiveData<ApiResponse<DetailTvShowResponse>> =
                remoteDataSource.getTvShowById(id)


            override fun saveCallResult(data: DetailTvShowResponse) {
                val genreBuilder = StringBuilder()
                val epsSeasonText =
                    "${data.number_of_episodes} Episodes | ${data.number_of_seasons} Season(s)"

                val iterator = data.genres.iterator()
                while (iterator.hasNext()) {
                    val obj = iterator.next()
                    if (iterator.hasNext()) {
                        genreBuilder.append(obj.name)
                        genreBuilder.append(", ")
                    } else {
                        genreBuilder.append(obj.name)
                    }
                }

                val tvShowDetail = TvShowEntity(
                    id = data.id,
                    backdrop_path = data.backdrop_path,
                    genres = genreBuilder.toString(),
                    overview = data.overview,
                    poster_path = data.poster_path,
                    release_date = data.first_air_date,
                    runtime = epsSeasonText,
                    title = data.name,
                    vote_average = data.vote_average,
                    isFav = false
                )
                localDataSource.updateTvShow(tvShowDetail, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavMovies(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavTvShow(), config).build()
    }

    override fun setFavoriteMovies(movie: MovieEntity, state: Boolean) {
            localDataSource.setFavoriteMovie(movie, state)
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
            localDataSource.setFavoriteTvShow(tvShow, state)
    }


}