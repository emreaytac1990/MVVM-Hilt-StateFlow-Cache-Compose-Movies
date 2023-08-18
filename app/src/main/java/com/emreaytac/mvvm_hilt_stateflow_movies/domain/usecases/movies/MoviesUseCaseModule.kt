package com.emreaytac.mvvm_hilt_stateflow_movies.domain.usecases.movies

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MoviesUseCaseModule {

    @Binds
    fun provideMoviesUseCaseImp(useCase: MoviesUseCaseImp): MoviesUseCase

}