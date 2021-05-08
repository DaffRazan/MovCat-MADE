package com.daffa.moviecatalogue.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.remote.RemoteDataSource
import com.daffa.moviecatalogue.data.source.remote.RemoteDataSourceTest
import com.daffa.moviecatalogue.data.source.remote.network.ApiResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.data.source.remote.response.MovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.TvShowResponse
import com.daffa.moviecatalogue.utils.DummyData
import com.daffa.moviecatalogue.utils.LiveDataTestUtil.getValue
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {

    private lateinit var repository: FakeMainRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var remote: RemoteDataSource

    @Mock
    lateinit var observerMovie: Observer<Resource<MovieResponse>>

    @Mock
    lateinit var observerTvShow: Observer<Resource<TvShowResponse>>

    @Mock
    lateinit var observerDetailMovie: Observer<Resource<DetailMovieResponse>>

    @Mock
    lateinit var observerDetailTvShow: Observer<Resource<DetailTvShowResponse>>

    @Before
    fun setUp() {
        repository = FakeMainRepository(remote)
    }

    // Movie Resources
    @Test
    fun movieResourceSuccess() {
        val dummyMovie = DummyData.dummyMovieResponse()

        `when`(remote.getMovies()).thenReturn(MutableLiveData(ApiResponse.Success(dummyMovie)))
        val resource = getValue(repository.getMovies())
        verify(remote).getMovies()

        repository.getMovies().observeForever(observerMovie)
        verify(observerMovie).onChanged(Resource.Success(dummyMovie))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Success)

        when (resource) {
            is Resource.Success -> {
                Assert.assertNotNull(resource.data)
                assertEquals(dummyMovie, resource.data)
                Assert.assertTrue(resource.data.results.isNotEmpty())
            }
        }
    }

    @Test
    fun movieResourceError() {
        `when`(remote.getMovies()).thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))
        val resource = getValue(repository.getMovies())
        verify(remote).getMovies()

        repository.getMovies().observeForever(observerMovie)
        verify(observerMovie).onChanged(Resource.Error(RemoteDataSourceTest.errorMessage))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Error)

        when (resource) {
            is Resource.Error -> {
                Assert.assertNotNull(resource.errorMessage)
                assertEquals(RemoteDataSourceTest.errorMessage, resource.errorMessage)
            }
        }
    }

    @Test
    fun movieResourceEmpty() {
        `when`(remote.getMovies()).thenReturn(MutableLiveData(ApiResponse.Empty(null)))
        val resource = getValue(repository.getMovies())
        verify(remote).getMovies()

        repository.getMovies().observeForever(observerMovie)
        verify(observerMovie).onChanged(Resource.Empty(null))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Empty)

        when (resource) {
            is Resource.Empty -> {
                Assert.assertNull(resource.data)
            }
        }
    }

    // TV SHOW Resources
    @Test
    fun tvShowResourceSuccess() {
        val dummyTvShow = DummyData.dummyTvShowsResponse()
        `when`(remote.getTvShows()).thenReturn(MutableLiveData(ApiResponse.Success(dummyTvShow)))
        val resource = getValue(repository.getTvShows())

        verify(remote).getTvShows()

        repository.getTvShows().observeForever(observerTvShow)
        verify(observerTvShow).onChanged(Resource.Success(dummyTvShow))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Success)

        when (resource) {
            is Resource.Success -> {
                Assert.assertNotNull(resource.data)
                assertEquals(dummyTvShow, resource.data)
                Assert.assertTrue(resource.data.results.isNotEmpty())
            }
        }
    }

    @Test
    fun tvShowResourceError() {
        `when`(remote.getTvShows())
            .thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))
        val resource = getValue(repository.getTvShows())
        verify(remote).getTvShows()

        repository.getTvShows().observeForever(observerTvShow)
        verify(observerTvShow).onChanged(Resource.Error(RemoteDataSourceTest.errorMessage))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Error)

        when (resource) {
            is Resource.Error -> {
                Assert.assertNotNull(resource.errorMessage)
                assertEquals(RemoteDataSourceTest.errorMessage, resource.errorMessage)
            }
        }
    }

    @Test
    fun tvShowResourceEmpty() {
        `when`(remote.getTvShows()).thenReturn(MutableLiveData(ApiResponse.Empty(null)))
        val resource = getValue(repository.getTvShows())
        verify(remote).getTvShows()

        repository.getTvShows().observeForever(observerTvShow)
        verify(observerTvShow).onChanged(Resource.Empty(null))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Empty)

        when (resource) {
            is Resource.Empty -> {
                Assert.assertNull(resource.data)
            }
        }
    }

    // Detail Movie Resources
    @Test
    fun detailMovieSourceSuccess() {
        val dummyDetailMovie = DummyData.dummyDetailMovieResponse()
        val idMovie = dummyDetailMovie.id
        `when`(remote.getMovieById(idMovie)).thenReturn(MutableLiveData(ApiResponse.Success(dummyDetailMovie)))
        val resource = getValue(repository.getMovieById(idMovie))

        verify(remote).getMovieById(idMovie)

        repository.getMovieById(idMovie).observeForever(observerDetailMovie)
        verify(observerDetailMovie).onChanged(Resource.Success(dummyDetailMovie))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Success)

        when (resource) {
            is Resource.Success -> {
                Assert.assertNotNull(resource.data)
                assertEquals(dummyDetailMovie, resource.data)
                Assert.assertTrue(resource.data.id == resource.data.id)
            }
        }
    }

    @Test
    fun detailMovieSourceError() {
        val idMovie = DummyData.dummyDetailMovieResponse().id
        `when`(remote.getMovieById(idMovie)).thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))
        val resource = getValue(repository.getMovieById(idMovie))
        verify(remote).getMovieById(idMovie)

        repository.getMovieById(idMovie)
            .observeForever(observerDetailMovie)
        verify(observerDetailMovie).onChanged(Resource.Error(RemoteDataSourceTest.errorMessage))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Error)
        when (resource) {
            is Resource.Error -> {
                Assert.assertNotNull(resource.errorMessage)
                assertEquals(RemoteDataSourceTest.errorMessage, resource.errorMessage)
            }
        }
    }

    @Test
    fun detailMovieSourceEmpty() {
        val idMovie = -500
        `when`(remote.getMovieById(idMovie)).thenReturn(MutableLiveData(ApiResponse.Empty(null)))
        val resource = getValue(repository.getMovieById(idMovie))
        verify(remote).getMovieById(idMovie)

        repository.getMovieById(idMovie)
            .observeForever(observerDetailMovie)
        verify(observerDetailMovie).onChanged(Resource.Empty(null))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Empty)
        when (resource) {
            is Resource.Empty -> {
                Assert.assertNull(resource.data)
            }
        }
    }

    // Detail Tv Show Resources
    @Test
    fun detailTvShowSourceSuccess() {
        val dummyDetailTvShow = DummyData.dummyDetailTvShowResponse()
        val idTvShow = dummyDetailTvShow.id
        `when`(remote.getTvShowById(idTvShow))
            .thenReturn(MutableLiveData(ApiResponse.Success(dummyDetailTvShow)))

        val resource = getValue(repository.getTvShowById(idTvShow))

        verify(remote).getTvShowById(idTvShow)

        repository.getTvShowById(idTvShow).observeForever(observerDetailTvShow)
        verify(observerDetailTvShow).onChanged(Resource.Success(dummyDetailTvShow))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Success)

        when (resource) {
            is Resource.Success -> {
                Assert.assertNotNull(resource.data)
                assertEquals(dummyDetailTvShow, resource.data)
                Assert.assertTrue(resource.data.id == resource.data.id)
            }
        }
    }

    @Test
    fun detailTvShowSourceError() {
        val idTvShow = DummyData.dummyDetailTvShowResponse().id
        `when`(remote.getTvShowById(idTvShow))
            .thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))
        val resource = getValue(repository.getTvShowById(idTvShow))

        verify(remote).getTvShowById(idTvShow)

        repository.getTvShowById(idTvShow).observeForever(observerDetailTvShow)
        verify(observerDetailTvShow).onChanged(Resource.Error(RemoteDataSourceTest.errorMessage))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Error)

        when (resource) {
            is Resource.Error -> {
                Assert.assertNotNull(resource.errorMessage)
                assertEquals(RemoteDataSourceTest.errorMessage, resource.errorMessage)
            }
        }
    }

    @Test
    fun detailTvShowSourceEmpty() {
        val idTvShow = -500
        `when`(remote.getTvShowById(idTvShow))
            .thenReturn(MutableLiveData(ApiResponse.Empty(null)))
        val resource = getValue(repository.getTvShowById(idTvShow))

        verify(remote).getTvShowById(idTvShow)

        repository.getTvShowById(idTvShow).observeForever(observerDetailTvShow)
        verify(observerDetailTvShow).onChanged(Resource.Empty(null))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Empty)

        when (resource) {
            is Resource.Empty -> {
                Assert.assertNull(resource.data)
            }
        }
    }

}