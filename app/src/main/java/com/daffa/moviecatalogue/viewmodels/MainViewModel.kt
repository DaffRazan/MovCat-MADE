package com.daffa.moviecatalogue.viewmodels

import androidx.lifecycle.ViewModel
import com.daffa.moviecatalogue.core.domain.usecase.MainUseCase

class MainViewModel (mainUseCase: MainUseCase) : ViewModel() {
    val getMovies = mainUseCase.getMovies()
    val getTvShows = mainUseCase.getTvShows()
}