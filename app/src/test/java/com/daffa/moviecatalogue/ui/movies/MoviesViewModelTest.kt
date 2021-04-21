package com.daffa.moviecatalogue.ui.movies

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp() {
        viewModel = MoviesViewModel()
    }

    @Test
    fun getMovies() {
        val moviesData = viewModel.getMovies()
        assertNotNull(moviesData)
        assertEquals(10, moviesData.size)
    }
}