package com.daffa.moviecatalogue.ui.detail

import com.daffa.moviecatalogue.utils.Constants.TYPE_MOVIE
import com.daffa.moviecatalogue.utils.Constants.TYPE_TVSHOW
import com.daffa.moviecatalogue.utils.DummyData
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class DetailFilmViewModelTest {
    private lateinit var viewModel: DetailFilmViewModel
    private val dummyMovie = DummyData.generateDummyMovies()[0]
    private val dummyTvShow = DummyData.generateDummyTvShows()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        viewModel = DetailFilmViewModel()
    }

    @Test
    fun getDataMovieById() {
        val movie = viewModel.getFilmById(movieId, TYPE_MOVIE)
        assertNotNull(movie)
        assertEquals(dummyMovie.title, movie.title)
        assertEquals(dummyMovie.imgPoster, movie.imgPoster)
        assertEquals(dummyMovie.description, movie.description)
        assertEquals(dummyMovie.releaseDate, movie.releaseDate)
        assertEquals(dummyMovie.genre, movie.genre)
        assertEquals(dummyMovie.score, movie.score)
    }

    @Test
    fun getDataTvShowById() {
        val tvShow = viewModel.getFilmById(tvShowId, TYPE_TVSHOW)
        assertNotNull(tvShow)
        assertEquals(dummyTvShow.title, tvShow.title)
        assertEquals(dummyTvShow.imgPoster, tvShow.imgPoster)
        assertEquals(dummyTvShow.description, tvShow.description)
        assertEquals(dummyTvShow.releaseDate, tvShow.releaseDate)
        assertEquals(dummyTvShow.genre, tvShow.genre)
        assertEquals(dummyTvShow.score, tvShow.score)
    }

}