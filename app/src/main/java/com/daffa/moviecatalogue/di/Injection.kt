package com.daffa.moviecatalogue.di

import android.content.Context
import com.daffa.moviecatalogue.data.repository.MainRepository
import com.daffa.moviecatalogue.data.source.local.LocalDataSource
import com.daffa.moviecatalogue.data.source.local.room.local.FilmDatabase
import com.daffa.moviecatalogue.data.source.remote.RemoteDataSource
import com.daffa.moviecatalogue.data.source.remote.network.ApiConfig
import com.daffa.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MainRepository {
        val db = FilmDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getAPIService())
        val localDataSource = LocalDataSource.getInstance(db.filmDao())
        val executors = AppExecutors()

        return MainRepository.getInstance(remoteDataSource, localDataSource, executors)
    }
}