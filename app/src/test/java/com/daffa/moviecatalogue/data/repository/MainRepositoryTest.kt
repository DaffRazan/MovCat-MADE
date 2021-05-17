package com.daffa.moviecatalogue.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.local.LocalDataSource
import com.daffa.moviecatalogue.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.data.source.local.entity.TvShowEntity
import com.daffa.moviecatalogue.data.source.remote.RemoteDataSource
import com.daffa.moviecatalogue.data.source.remote.network.ApiResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.data.source.remote.response.MovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.TvShowResponse
import com.daffa.moviecatalogue.utils.AppExecutors
import com.daffa.moviecatalogue.utils.DummyData
import com.daffa.moviecatalogue.utils.LiveDataTestUtil
import com.daffa.moviecatalogue.utils.LiveDataTestUtil.getValue
import com.daffa.moviecatalogue.utils.PagedListUtil
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {

    // setting fake repo
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val fakeRepository = FakeMainRepository(remote, local, appExecutors)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

   // remote movie and tv show responses
    private val remoteMovie = DummyData.getRemoteMovies()
    private val movieId = remoteMovie[0].id
    private val detailRemoteMovie = DummyData.getRemoteDetailMovie()

    private val remoteTvShow = DummyData.getRemoteTvShows()
    private val tvShowId = remoteTvShow[0].id
    private val detailRemoteTvShow = DummyData.getRemoteDetailTvShow()

    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies("NEWEST")).thenReturn(dataSourceFactory)
        fakeRepository.getMovies("NEWEST")

        val movieEnt = Resource.success(PagedListUtil.mockPagedList(DummyData.getMovies()))
        verify(local).getAllMovies("NEWEST")
        assertNotNull(movieEnt)
        assertEquals(remoteMovie.size, movieEnt.data?.size)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = MutableLiveData<MovieEntity>()
        dummyDetailMovie.value = DummyData.getDetailMovie()
        `when`(local.getMovieById(movieId)).thenReturn(dummyDetailMovie)

        val detailMovieEnt = LiveDataTestUtil.getValue(fakeRepository.getMovieById(movieId))
        verify(local).getMovieById(movieId)
        assertNotNull(detailMovieEnt)

        assertEquals(detailRemoteMovie.id, detailMovieEnt.data?.id)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,MovieEntity>
        `when`(local.getFavMovies()).thenReturn(dataSourceFactory)
        fakeRepository.getFavoriteMovies()

        val movieEnt = Resource.success(PagedListUtil.mockPagedList(DummyData.getMovies()))
        verify(local).getFavMovies()
        assertNotNull(movieEnt)
        assertEquals(remoteMovie.size, movieEnt.data?.size)
    }

    @Test
    fun setFavoriteMovie() {
        fakeRepository.setFavoriteMovies(DummyData.getDetailMovie(), true)
        verify(local).setFavoriteMovie(DummyData.getDetailMovie(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getTvShows() {
        val dsFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,TvShowEntity>
        `when`(local.getAllTvShows("NEWEST")).thenReturn(dsFactory)
        fakeRepository.getTvShows("NEWEST")

        val tvShowEnt = Resource.success(PagedListUtil.mockPagedList(DummyData.getTvShows()))
        verify(local).getAllTvShows("NEWEST")
        assertNotNull(tvShowEnt)
        assertEquals(remoteTvShow.size, tvShowEnt.data?.size)
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetailTvShow = MutableLiveData<TvShowEntity>()
        dummyDetailTvShow.value = DummyData.getDetailTvShow()
        `when`(local.getTvShowById(tvShowId)).thenReturn(dummyDetailTvShow)

        val detailTvShowEnt = LiveDataTestUtil.getValue(fakeRepository.getTvShowById(tvShowId))
        verify(local).getTvShowById(tvShowId)
        assertNotNull(detailTvShowEnt)
        assertEquals(detailRemoteTvShow.id, detailTvShowEnt.data?.id)
    }

    @Test
    fun getFavoriteTvShow() {
        val dsFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavTvShow()).thenReturn(dsFactory)
        fakeRepository.getFavoriteTvShows()

        val tvShowEnt = Resource.success(PagedListUtil.mockPagedList(DummyData.getTvShows()))
        verify(local).getFavTvShow()
        assertNotNull(tvShowEnt)
        assertEquals(remoteTvShow.size, tvShowEnt.data?.size)
    }

    @Test
    fun setFavoriteTvShow() {
        fakeRepository.setFavoriteTvShow(DummyData.getDetailTvShow(), true)
        verify(local).setFavoriteTvShow(DummyData.getDetailTvShow(), true)
        verifyNoMoreInteractions(local)
    }
}