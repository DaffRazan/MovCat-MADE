package com.daffa.moviecatalogue.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.daffa.moviecatalogue.data.repository.MainRepository
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.data.source.local.entity.TvShowEntity
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
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

    @Mock
    private lateinit var mainRepository: MainRepository

    @Mock
    lateinit var observerMovie: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    lateinit var observerTvShow: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieEntity>

    @Mock
    private lateinit var pagedListTvShow: PagedList<TvShowEntity>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(mainRepository)
    }

    // Uji data lokal (dummy)
    @Test
    fun getMovies(){
        val dummyMovies = Resource.success(pagedListMovie)
        `when`(dummyMovies.data?.size).thenReturn(3)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(mainRepository.getMovies("NEWEST")).thenReturn(movies)
        val movie = mainViewModel.getMovies("NEWEST").value?.data
        verify(mainRepository).getMovies("NEWEST")
        assertNotNull(movie)
        assertEquals(3, movie?.size)

        mainViewModel.getMovies("NEWEST").observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovies)
    }

    @Test
    fun getTvShows() {
        val dummyTvShow = Resource.success(pagedListTvShow)
        `when`(dummyTvShow.data?.size).thenReturn(3)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShow

        `when`(mainRepository.getTvShows("NEWEST")).thenReturn(tvShows)
        val tvShow = mainViewModel.getTvShows("NEWEST").value?.data
        verify(mainRepository).getTvShows("NEWEST")
        assertNotNull(tvShow)
        assertEquals(3, tvShow?.size)

        mainViewModel.getTvShows("NEWEST").observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyTvShow)
    }

}


