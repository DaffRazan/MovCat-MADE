package com.daffa.moviecatalogue.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daffa.moviecatalogue.core.domain.usecase.MainUseCase
import com.daffa.moviecatalogue.core.di.Injection
import com.daffa.moviecatalogue.viewmodels.DetailFilmViewModel
import com.daffa.moviecatalogue.viewmodels.FavoriteViewModel
import com.daffa.moviecatalogue.viewmodels.MainViewModel

class ViewModelFactory private constructor(private val mainUseCase: MainUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideMainUseCase(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                return MainViewModel(mainUseCase) as T
            }
            modelClass.isAssignableFrom(DetailFilmViewModel::class.java) -> {
                return DetailFilmViewModel(mainUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                return FavoriteViewModel(mainUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}