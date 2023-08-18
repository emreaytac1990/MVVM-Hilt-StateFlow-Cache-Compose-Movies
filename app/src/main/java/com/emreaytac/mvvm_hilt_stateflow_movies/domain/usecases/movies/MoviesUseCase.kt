package com.emreaytac.mvvm_hilt_stateflow_movies.domain.usecases.movies

import androidx.paging.PagingData
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.models.Movie
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.usecases.UseCase
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase : UseCase<MoviesUseCase.MoviesParams, Flow<PagingData<Movie>>> {

    data class MoviesParams(val page: Int)
}