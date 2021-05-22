package com.daffa.moviecatalogue.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.daffa.moviecatalogue.data.repository.MainRepository
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.data.source.local.entity.TvShowEntity
import com.daffa.moviecatalogue.utils.DummyData
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.MOVIE
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel.Companion.TV_SHOW
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailFilmViewModelTest {
    private lateinit var viewModel: DetailFilmViewModel

    // prepare dummy data movies and tv show
    private val dummyMovie = DummyData.getDetailMovie()
    private val dummyMovieId = dummyMovie.id

    private val dummyTvShow = DummyData.getDetailMovie()
    private val dummyTvShowId = dummyTvShow.id

    // mock
    @Mock
    lateinit var repository: MainRepository

    @Mock
    lateinit var observerDetailMovie: Observer<Resource<MovieEntity>>

    @Mock
    lateinit var observerDetailTvShow: Observer<Resource<TvShowEntity>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = DetailFilmViewModel(repository)
    }

    // Detail movie testing
    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = Resource.success(DummyData.getDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(repository.getMovieById(dummyMovieId)).thenReturn(movie)
        viewModel.setFilm(dummyMovieId.toString(), MOVIE)
        viewModel.getDetailMovie.observeForever(observerDetailMovie)
        verify(observerDetailMovie).onChanged(dummyDetailMovie)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyDetailMovie = Resource.success(DummyData.getDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(repository.getMovieById(dummyMovieId)).thenReturn(movie)
        viewModel.setFilm(dummyMovieId.toString(), MOVIE)
        viewModel.setFavoriteMovie()
        verify(repository).setFavoriteMovies(movie.value!!.data as MovieEntity, true)

    }

    // Detail TV Show testing
    @Test
    fun getDetailTvShow() {
        val dummyDetailTvShow = Resource.success(DummyData.getDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow
        `when`(repository.getTvShowById(dummyTvShowId)).thenReturn(tvShow)

        viewModel.setFilm(dummyTvShowId.toString(), TV_SHOW)
        viewModel.getDetailTvShow.observeForever(observerDetailTvShow)
        verify(observerDetailTvShow).onChanged(dummyDetailTvShow)
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyDetailTvShow = Resource.success(DummyData.getDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow
        `when`(repository.getTvShowById(dummyTvShowId)).thenReturn(tvShow)

        viewModel.setFilm(dummyTvShowId.toString(), TV_SHOW)
        viewModel.setFavoriteTvShow()
        verify(repository).setFavoriteTvShow(tvShow.value!!.data as TvShowEntity, true)
    }

}
