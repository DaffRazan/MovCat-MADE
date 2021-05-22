package com.daffa.moviecatalogue.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.daffa.moviecatalogue.data.repository.MainRepository
import com.daffa.moviecatalogue.data.source.Resource
import com.daffa.moviecatalogue.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.data.source.local.entity.TvShowEntity
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    fun getMovies(sort: String) : LiveData<Resource<PagedList<MovieEntity>>> = mainRepository.getMovies(sort)
    fun getTvShows(sort: String) : LiveData<Resource<PagedList<TvShowEntity>>> = mainRepository.getTvShows(sort)
}