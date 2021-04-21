package com.daffa.moviecatalogue.ui.movies

import androidx.lifecycle.ViewModel
import com.daffa.moviecatalogue.data.FilmEntity
import com.daffa.moviecatalogue.utils.DummyData

class MoviesViewModel : ViewModel() {

    fun getMovies(): List<FilmEntity> = DummyData.generateDummyMovies()

}