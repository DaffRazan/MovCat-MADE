package com.daffa.moviecatalogue.ui.tvshows

import com.daffa.moviecatalogue.ui.movies.MoviesViewModel
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TvShowsViewModelTest {

    private lateinit var viewModel: TvShowsViewModel

    @Before
    fun setUp() {
        viewModel = TvShowsViewModel()
    }

    @Test
    fun getTvShows() {
        val tvShowsData = viewModel.getTvShows()
        assertNotNull(tvShowsData)
        assertEquals(10, tvShowsData.size)

    }
}