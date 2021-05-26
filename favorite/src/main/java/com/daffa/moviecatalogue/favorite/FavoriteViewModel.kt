package com.daffa.moviecatalogue.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.daffa.moviecatalogue.core.data.source.Resource
import com.daffa.moviecatalogue.core.domain.model.Movie
import com.daffa.moviecatalogue.core.domain.model.TvShow
import com.daffa.moviecatalogue.core.domain.usecase.MainUseCase

class FavoriteViewModel(useCase: MainUseCase) : ViewModel() {
    val getFavoriteMovies = useCase.getFavoriteMovies().asLiveData()
    val getFavoriteTvShows = useCase.getFavoriteTvShows().asLiveData()
}