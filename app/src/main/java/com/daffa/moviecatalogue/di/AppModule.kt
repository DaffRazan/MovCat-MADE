package com.daffa.moviecatalogue.di

import com.daffa.moviecatalogue.core.domain.usecase.MainInteractor
import com.daffa.moviecatalogue.core.domain.usecase.MainUseCase
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel
import com.daffa.moviecatalogue.viewmodels.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MainUseCase> { MainInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailFilmViewModel(get()) }
}
