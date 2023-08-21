package com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movie_detail

import com.emreaytac.mvvm_hilt_stateflow_movies.domain.Resource
import com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movies.api.MoviesApiService
import com.emreaytac.mvvm_hilt_stateflow_movies.data.repository.BaseDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MovieDetailDataSource @Inject constructor(private val apiService: MoviesApiService): BaseDataSource() {
    suspend fun getMovieDetail(movieId: Int) : Flow<Resource<MovieDetailResponse>> = flow{
        emit( safeApiCall { apiService.getMovieDetail(movieId.toString()) } )
    }.flowOn(Dispatchers.IO)

}