package com.daffa.moviecatalogue.core.di

import androidx.room.Room
import com.daffa.moviecatalogue.core.data.repository.MainRepository
import com.daffa.moviecatalogue.core.data.source.local.LocalDataSource
import com.daffa.moviecatalogue.core.data.source.local.room.local.FilmDatabase
import com.daffa.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.daffa.moviecatalogue.core.data.source.remote.network.ApiService
import com.daffa.moviecatalogue.core.domain.repository.IMainRepository
import com.daffa.moviecatalogue.core.utils.AppExecutors
import com.daffa.moviecatalogue.core.utils.Constants
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<FilmDatabase>().filmDao() }

    single {
        // encryption
        val passphrase: ByteArray = SQLiteDatabase.getBytes("movcat".toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            FilmDatabase::class.java,
            "Film.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        // certificate pinning
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .build()

        OkHttpClient.Builder()
            .connectTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMainRepository> {
       MainRepository(
            get(),
            get(),
            get()
        )
    }
}