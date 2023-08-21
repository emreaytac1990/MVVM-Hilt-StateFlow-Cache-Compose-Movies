package com.emreaytac.mvvm_hilt_stateflow_movies.domain.repository.movie_detail

import com.emreaytac.mvvm_hilt_stateflow_movies.domain.Resource
import com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movie_detail.MovieDetailResponse
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieID: Int): Flow<Resource<MovieDetailResponse>>
}