package com.emreaytac.mvvm_hilt_stateflow_movies.domain.usecases.movies

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.emreaytac.mvvm_hilt_stateflow_movies.data.local.movies.MoviesDatabase
import com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movies.MoviesRemoteMediator
import com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movies.api.MoviesApiService
import com.emreaytac.mvvm_hilt_stateflow_movies.data.utils.Constants.MOVIES_LIMIT
import com.emreaytac.mvvm_hilt_stateflow_movies.data.utils.toMovies
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesUseCaseImpl {

}

class MoviesUseCaseImp @Inject constructor(
    private val apiService: MoviesApiService,
    private val database: MoviesDatabase
) : MoviesUseCase {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun execute(param: MoviesUseCase.MoviesParams): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = MOVIES_LIMIT,
                prefetchDistance = 10,
                initialLoadSize = MOVIES_LIMIT,
            ),
            pagingSourceFactory = {
                database.dao.pagingSource()
            },
            remoteMediator = MoviesRemoteMediator(
                apiService = apiService,
                moviesDatabase = database,
            )
        ).flow.map { pagingData ->
            pagingData.map { it.toMovies() }
        }
    }


}