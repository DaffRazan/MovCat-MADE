package com.daffa.moviecatalogue.di

import android.content.Context
import com.daffa.moviecatalogue.core.data.repository.MainRepository
import com.daffa.moviecatalogue.core.data.source.local.LocalDataSource
import com.daffa.moviecatalogue.core.data.source.local.room.local.FilmDatabase
import com.daffa.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.daffa.moviecatalogue.core.data.source.remote.network.ApiConfig
import com.daffa.moviecatalogue.core.domain.repository.IMainRepository
import com.daffa.moviecatalogue.core.domain.usecase.MainInteractor
import com.daffa.moviecatalogue.core.domain.usecase.MainUseCase
import com.daffa.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): IMainRepository {
        val db = FilmDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getAPIService())
        val localDataSource = LocalDataSource.getInstance(db.filmDao())
        val executors = AppExecutors()

        return MainRepository.getInstance(remoteDataSource, localDataSource, executors)
    }

    fun provideMainUseCase(context: Context): MainUseCase {
        val repo = provideRepository(context)
        return MainInteractor(repo)
    }
}