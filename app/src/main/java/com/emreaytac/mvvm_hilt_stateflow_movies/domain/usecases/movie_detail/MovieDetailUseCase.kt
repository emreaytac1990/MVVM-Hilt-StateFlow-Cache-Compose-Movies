package com.emreaytac.mvvm_hilt_stateflow_movies.domain.usecases.movie_detail

import com.emreaytac.mvvm_hilt_stateflow_movies.domain.Resource
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.models.MovieDetail
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.usecases.UseCase
import kotlinx.coroutines.flow.Flow

interface MovieDetailUseCase: UseCase<MovieDetailUseCase.MovieDetailParams, Flow<Resource<MovieDetail>>> {
    data class MovieDetailParams(val movieId: Int)
}