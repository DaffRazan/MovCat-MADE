package com.daffa.moviecatalogue.core.utils

import com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity
import com.daffa.moviecatalogue.core.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.core.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.core.domain.model.Movie
import com.daffa.moviecatalogue.core.domain.model.TvShow

object DataMapper {
    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                adult = it.adult,
                backdrop_path = it.backdrop_path,
                genres = it.genres,
                overview = it.overview,
                poster_path = it.poster_path,
                release_date = it.release_date,
                runtime = it.runtime,
                title = it.title,
                vote_average = it.vote_average,
                isFavorite = it.isFavorite
            )
        }

    fun mapMovieDomainToEntities(input: Movie) = MovieEntity(
        id = input.id,
        adult = input.adult,
        backdrop_path = input.backdrop_path,
        genres = input.genres,
        overview = input.overview,
        poster_path = input.poster_path,
        release_date = input.release_date,
        runtime = input.runtime,
        title = input.title,
        vote_average = input.vote_average,
        isFavorite = input.isFavorite
    )

    fun mapMovieResponseToEntities(input: List<com.daffa.moviecatalogue.core.data.source.remote.response.model.Movie>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                backdrop_path = it.backdrop_path,
                genres = "",
                overview = it.overview,
                poster_path = it.poster_path,
                release_date = it.release_date,
                runtime = 0,
                title = it.title,
                vote_average = it.vote_average,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapTvShowEntityToDomain(input: List<TvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                id = it.id,
                backdrop_path = it.backdrop_path,
                genres = it.genres,
                overview = it.overview,
                poster_path = it.poster_path,
                release_date = it.release_date,
                runtime = it.runtime,
                title = it.title,
                vote_average = it.vote_average,
                isFavorite = it.isFavorite
            )
        }

    fun mapTvShowDomainToEntities(input: TvShow) = TvShowEntity(
        id = input.id,
        backdrop_path = input.backdrop_path,
        genres = input.genres,
        overview = input.overview,
        poster_path = input.poster_path,
        release_date = input.release_date,
        runtime = input.runtime,
        title = input.title,
        vote_average = input.vote_average,
        isFavorite = input.isFavorite
    )

    fun mapTvShowResponseToEntities(input: List<com.daffa.moviecatalogue.core.data.source.remote.response.model.TvShow>): List<TvShowEntity> {
        val tvShowList = ArrayList<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
                id = it.id,
                backdrop_path = it.backdrop_path,
                genres = "",
                overview = it.overview,
                poster_path = it.poster_path,
                release_date = it.first_air_date,
                runtime = "",
                title = it.name,
                vote_average = it.vote_average,
                isFavorite = false
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapDetailMovieEntityToDomain(input: MovieEntity) =
        Movie(
            id = input.id,
            adult = input.adult,
            backdrop_path = input.backdrop_path,
            genres = input.genres,
            overview = input.overview,
            poster_path = input.poster_path,
            release_date = input.release_date,
            runtime = input.runtime,
            title = input.title,
            vote_average = input.vote_average,
            isFavorite = input.isFavorite
        )

    fun mapDetailTvShowEntityToDomain(input: TvShowEntity) =
        TvShow(
            id = input.id,
            backdrop_path = input.backdrop_path,
            genres = input.genres,
            overview = input.overview,
            poster_path = input.poster_path,
            release_date = input.release_date,
            runtime = input.runtime,
            title = input.title,
            vote_average = input.vote_average,
            isFavorite = input.isFavorite
        )

    fun mapDetailMovieResponseToEntity(input: DetailMovieResponse): MovieEntity {
        val genreBuilder = StringBuilder()

        val iterator = input.genres.iterator()
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
            id = input.id,
            backdrop_path = input.backdrop_path,
            genres = genreBuilder.toString(),
            overview = input.overview,
            poster_path = input.poster_path,
            release_date = input.release_date,
            runtime = input.runtime,
            title = input.title,
            vote_average = input.vote_average,
            isFavorite = false
        )
        return movieDetail
    }

    fun mapDetailTvShowResponseToEntity(input: DetailTvShowResponse): TvShowEntity {
        val genreBuilder = StringBuilder()
        val epsSeasonText =
            "${input.number_of_episodes} Episodes | ${input.number_of_seasons} Season(s)"

        val iterator = input.genres.iterator()
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
            id = input.id,
            backdrop_path = input.backdrop_path,
            genres = genreBuilder.toString(),
            overview = input.overview,
            poster_path = input.poster_path,
            release_date = input.first_air_date,
            runtime = epsSeasonText,
            title = input.name,
            vote_average = input.vote_average,
            isFavorite = false
        )
        return tvShowDetail
    }

}
