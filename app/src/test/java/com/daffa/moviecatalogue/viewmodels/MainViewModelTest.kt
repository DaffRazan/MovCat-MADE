package com.daffa.moviecatalogue.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.daffa.moviecatalogue.data.repository.MainRepository
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.remote.response.MovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.TvShowResponse
import com.daffa.moviecatalogue.utils.DummyData
import com.daffa.moviecatalogue.utils.LiveDataTestUtil.getValue
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private val dummyDataMovie = DummyData.dummyMovieResponse()
    private val dummyDataTvShow = DummyData.dummyTvShowsResponse()

    @Mock
    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var observerMovie: Observer<Resource<MovieResponse>>

    @Mock
    lateinit var observerTvShow: Observer<Resource<TvShowResponse>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(mainRepository)
    }

    // Uji data lokal (dummy)

    // Resource Movie
    @Test
    fun getResourceMovieSuccess() {
        `when`(mainRepository.getMovies())
            .thenReturn(MutableLiveData(Resource.Success(dummyDataMovie)))
        val resource = getValue(mainViewModel.getMovies)
        verify(mainRepository).getMovies()
        assertTrue(resource is Resource.Success)
        when (resource) {
            is Resource.Success -> {
                assertEquals(dummyDataMovie, resource.data)
            }
        }

        mainViewModel.getMovies.observeForever(observerMovie)
        verify(observerMovie).onChanged(Resource.Success(dummyDataMovie))
    }

    @Test
    fun getResourceMovieEmpty() {
        `when`(mainRepository.getMovies()).thenReturn(MutableLiveData(Resource.Empty(null)))
        val resource = getValue(mainViewModel.getMovies)
        verify(mainRepository).getMovies()
        Assert.assertTrue(resource is Resource.Empty)
        when (resource) {
            is Resource.Empty -> {
                Assert.assertNull(resource.data)
            }
        }

        mainViewModel.getMovies.observeForever(observerMovie)
        verify(observerMovie).onChanged(Resource.Empty(null))
    }

    //Resource TV Shows
    @Test
    fun getResourceTvShowSuccess() {
        `when`(mainRepository.getTvShows()).thenReturn(
            MutableLiveData(
                Resource.Success(
                    dummyDataTvShow
                )
            )
        )
        val resource = getValue(mainViewModel.getTvShows)
        verify(mainRepository).getTvShows()
        Assert.assertTrue(resource is Resource.Success)
        when (resource) {
            is Resource.Success -> {
                assertEquals(dummyDataTvShow, resource.data)
            }
        }

        mainViewModel.getTvShows.observeForever(observerTvShow)
        verify(observerTvShow).onChanged(Resource.Success(dummyDataTvShow))
    }


    @Test
    fun getResourceTvShowEmpty() {
        `when`(mainRepository.getTvShows()).thenReturn(MutableLiveData(Resource.Empty(null)))
        val resource = getValue(mainViewModel.getTvShows)
        verify(mainRepository).getTvShows()
        Assert.assertTrue(resource is Resource.Empty)
        when (resource) {
            is Resource.Empty -> {
                Assert.assertNull(resource.data)
            }
        }

        mainViewModel.getTvShows.observeForever(observerTvShow)
        verify(observerTvShow).onChanged(Resource.Empty(null))
    }
}


