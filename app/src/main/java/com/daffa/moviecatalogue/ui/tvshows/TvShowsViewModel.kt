package com.daffa.moviecatalogue.ui.tvshows

import androidx.lifecycle.ViewModel
import com.daffa.moviecatalogue.data.FilmEntity
import com.daffa.moviecatalogue.utils.DummyData

class TvShowsViewModel: ViewModel() {
    fun getTvShows(): List<FilmEntity> = DummyData.generateDummyTvShows()
}