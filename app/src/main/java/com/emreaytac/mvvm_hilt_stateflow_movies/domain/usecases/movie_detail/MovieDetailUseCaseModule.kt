package com.emreaytac.mvvm_hilt_stateflow_movies.domain.usecases.movie_detail

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MovieDetailUseCaseModule {

    @Binds
    fun provideMovieDetailUseCaseImpl(useCase: MovieDetailUseCaseImpl): MovieDetailUseCase

}