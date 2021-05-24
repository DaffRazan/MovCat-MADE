package com.daffa.moviecatalogue.core.utils

import com.daffa.moviecatalogue.core.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.core.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.core.domain.model.Movie
import com.daffa.moviecatalogue.core.domain.model.TvShow

object DataMapper {
    fun mapMovieEntitiesToDomain(input: List<com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity>): List<Movie> =
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

    fun mapMovieDomainToEntities(input: Movie) =
        com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity(
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

    fun mapMovieResponseToEntities(input: List<com.daffa.moviecatalogue.core.data.source.remote.response.model.Movie>): List<com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity> {
        val movieList = ArrayList<com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity>()
        input.map {
            val movie = com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity(
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

    fun mapTvShowEntityToDomain(input: List<com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity>): List<TvShow> =
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

    fun mapTvShowDomainToEntities(input: TvShow) =
        com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity(
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

    fun mapTvShowResponseToEntities(input: List<com.daffa.moviecatalogue.core.data.source.remote.response.model.TvShow>): List<com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity> {
        val tvShowList = ArrayList<com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity>()
        input.map {
            val tvShow = com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity(
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

    fun mapDetailMovieEntityToDomain(input: com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity) =
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

    fun mapDetailTvShowEntityToDomain(input: com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity) =
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

    fun mapDetailMovieResponseToEntity(input: DetailMovieResponse): com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity {
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

        val movieDetail = com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity(
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

    fun mapDetailTvShowResponseToEntity(input: DetailTvShowResponse): com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity {
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

        val tvShowDetail = com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity(
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
