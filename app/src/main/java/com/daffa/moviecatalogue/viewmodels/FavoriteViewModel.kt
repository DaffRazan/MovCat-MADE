package com.daffa.moviecatalogue.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.daffa.moviecatalogue.core.domain.usecase.MainUseCase

class FavoriteViewModel(useCase: MainUseCase) : ViewModel() {
    val getFavoriteMovies = useCase.getFavoriteMovies().asLiveData()
    val getFavoriteTvShows = useCase.getFavoriteTvShows().asLiveData()
}