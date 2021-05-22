package com.daffa.moviecatalogue.viewmodels

import androidx.lifecycle.ViewModel
import com.daffa.moviecatalogue.data.repository.MainRepository
import com.daffa.moviecatalogue.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.data.source.local.entity.TvShowEntity

class FavoriteViewModel(private val repo: MainRepository) : ViewModel() {
    fun getFavoriteMovies() = repo.getFavoriteMovies()
    fun getFavoriteTvShows() = repo.getFavoriteTvShows()

    fun setFavoriteMovie(movieEnt: MovieEntity) {
        val newState = !movieEnt.isFavorite
        repo.setFavoriteMovies(movieEnt, newState)
    }

    fun setFavoriteTvShow(tvShowEnt: TvShowEntity) {
        val newState = !tvShowEnt.isFavorite
        repo.setFavoriteTvShow(tvShowEnt, newState)
    }

    val getFavMovies by lazy { getFavoriteMovies() }
    val getFavTvShows by lazy { getFavoriteTvShows() }
}