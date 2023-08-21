package com.emreaytac.mvvm_hilt_stateflow_movies.data.repository.movie_detail

import com.emreaytac.mvvm_hilt_stateflow_movies.domain.Resource
import com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movie_detail.MovieDetailDataSource
import com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movie_detail.MovieDetailResponse
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.repository.movie_detail.MovieDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(private val dataSource: MovieDetailDataSource): MovieDetailRepository {
    override suspend fun getMovieDetail(movieID: Int): Flow<Resource<MovieDetailResponse>> = dataSource.getMovieDetail(movieID)

}