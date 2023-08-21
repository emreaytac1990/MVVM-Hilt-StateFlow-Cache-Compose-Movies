package com.emreaytac.mvvm_hilt_stateflow_movies.domain.usecases.movie_detail

import com.emreaytac.mvvm_hilt_stateflow_movies.domain.Resource
import com.emreaytac.mvvm_hilt_stateflow_movies.data.utils.toMovieDetail
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.models.MovieDetail
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.repository.movie_detail.MovieDetailRepository
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.utils.toNewResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailUseCaseImpl @Inject constructor(private val repository: MovieDetailRepository): MovieDetailUseCase {
    override suspend fun execute(param: MovieDetailUseCase.MovieDetailParams): Flow<Resource<MovieDetail>> {
        return repository.getMovieDetail(param.movieId).toNewResource {
            Resource.Success(it.toMovieDetail())
        }
    }
}