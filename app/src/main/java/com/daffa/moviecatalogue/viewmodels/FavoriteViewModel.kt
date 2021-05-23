package com.daffa.moviecatalogue.viewmodels

import androidx.lifecycle.ViewModel
import com.daffa.moviecatalogue.core.domain.usecase.MainUseCase

class FavoriteViewModel(useCase: MainUseCase) : ViewModel() {
    val getFavoriteMovies = useCase.getFavoriteMovies()
    val getFavoriteTvShows = useCase.getFavoriteTvShows()
}