package com.emreaytac.mvvm_hilt_stateflow_movies.data.repository.movie_detail

import com.emreaytac.mvvm_hilt_stateflow_movies.domain.repository.movie_detail.MovieDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface MovieDetailRepositoryModule {

    @Binds
    fun providePokemonRepositoryImpl(repository: MovieDetailRepositoryImpl): MovieDetailRepository
}