package com.daffa.moviecatalogue.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.daffa.moviecatalogue.core.domain.usecase.MainUseCase

class MainViewModel (mainUseCase: MainUseCase) : ViewModel() {
    val getMovies = mainUseCase.getMovies().asLiveData()
    val getTvShows = mainUseCase.getTvShows().asLiveData()
}