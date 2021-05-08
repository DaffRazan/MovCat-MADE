package com.daffa.moviecatalogue.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.daffa.moviecatalogue.data.repository.MainRepository
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.utils.DummyData
import com.daffa.moviecatalogue.utils.LiveDataTestUtil.getValue
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailFilmViewModelTest {
    private lateinit var viewModel: DetailFilmViewModel

    @Mock
    lateinit var repository: MainRepository
    @Mock
    lateinit var observerDetailMovie: Observer<Resource<DetailMovieResponse>>
    @Mock
    lateinit var observerDetailTvShow: Observer<Resource<DetailTvShowResponse>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = DetailFilmViewModel(repository)
    }

    @Test
    fun getDetailMovieResourceSuccess() {
        val dummyData = DummyData.dummyDetailMovieResponse()
        val idMovie = dummyData.id
        val categoryMovie = DetailFilmViewModel.MOVIE

        Mockito.`when`(repository.getMovieById(idMovie))
            .thenReturn(MutableLiveData(Resource.Success(dummyData)))

        viewModel.setFilm(idMovie.toString(), categoryMovie)

        verify(repository).getMovieById(idMovie)
        assertNotNull(viewModel.detailMovie)

        viewModel.detailMovie.observeForever(observerDetailMovie)
        verify(observerDetailMovie).onChanged(Resource.Success(dummyData))

        val resource = getValue(viewModel.getDetailMovie)
        assertNotNull(resource)
        assertTrue(resource is Resource.Success)
        when (resource) {
            is Resource.Success -> {
                assertEquals(dummyData, resource.data)
                assertEquals(idMovie, resource.data.id)
            }
        }
    }

    @Test
    fun getDetailTvShowResourceSuccess() {
        val dummyData = DummyData.dummyDetailTvShowResponse()
        val idTvShow = dummyData.id
        val categoryTvShow = DetailFilmViewModel.TV_SHOW

        Mockito.`when`(repository.getTvShowById(idTvShow))
            .thenReturn(MutableLiveData(Resource.Success(dummyData)))

        viewModel.setFilm(idTvShow.toString(), categoryTvShow)

        verify(repository).getTvShowById(idTvShow)
        assertNotNull(viewModel.detailTvShow)

        viewModel.detailTvShow.observeForever(observerDetailTvShow)
        verify(observerDetailTvShow).onChanged(Resource.Success(dummyData))

        val resource = getValue(viewModel.getDetailTvShow)
        assertNotNull(resource)
        assertTrue(resource is Resource.Success)
        when (resource) {
            is Resource.Success -> {
                assertEquals(dummyData, resource.data)
                assertEquals(idTvShow, resource.data.id)
            }
        }
    }

}
