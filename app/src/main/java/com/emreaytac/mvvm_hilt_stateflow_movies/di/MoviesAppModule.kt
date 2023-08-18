package com.emreaytac.mvvm_hilt_stateflow_movies.di

import android.content.Context
import androidx.room.Room
import com.emreaytac.mvvm_hilt_stateflow_movies.BuildConfig
import com.emreaytac.mvvm_hilt_stateflow_movies.data.local.movies.MoviesDao
import com.emreaytac.mvvm_hilt_stateflow_movies.data.local.movies.MoviesDatabase
import com.emreaytac.mvvm_hilt_stateflow_movies.data.local.movies.MoviesRemoteKeysDao
import com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movies.api.MoviesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesAppModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MoviesApiService {
        return retrofit.create(MoviesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesDatabase(@ApplicationContext appContext: Context): MoviesDatabase {
        return Room.databaseBuilder(
            appContext,
            MoviesDatabase::class.java,
            "movies.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(moviesDatabase: MoviesDatabase): MoviesDao {
        return moviesDatabase.dao
    }

    @Singleton
    @Provides
    fun provideRemoteKeysDao(moviesDatabase: MoviesDatabase): MoviesRemoteKeysDao {
        return moviesDatabase.remoteKeysDao
    }
}