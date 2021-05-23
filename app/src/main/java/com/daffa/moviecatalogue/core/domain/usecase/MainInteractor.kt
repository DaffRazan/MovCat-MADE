package com.daffa.moviecatalogue.core.domain.usecase

import com.daffa.moviecatalogue.core.domain.model.Movie
import com.daffa.moviecatalogue.core.domain.model.TvShow
import com.daffa.moviecatalogue.core.domain.repository.IMainRepository

class MainInteractor(private val mainRepository: IMainRepository) : MainUseCase {

    override fun getMovies() = mainRepository.getMovies()
    override fun getTvShows() = mainRepository.getTvShows()

    override fun getMovieById(id: Int) = mainRepository.getMovieById(id)
    override fun getTvShowById(id: Int) = mainRepository.getTvShowById(id)

    override fun getFavoriteMovies() = mainRepository.getFavoriteMovies()
    override fun getFavoriteTvShows() = mainRepository.getFavoriteTvShows()

    override fun setFavoriteMovies(movie: Movie, state: Boolean) =
        mainRepository.setFavoriteMovies(movie, state)

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) =
        mainRepository.setFavoriteTvShow(tvShow, state)
}