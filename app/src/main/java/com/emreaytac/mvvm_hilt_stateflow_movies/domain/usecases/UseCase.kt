package com.emreaytac.mvvm_hilt_stateflow_movies.domain.usecases

interface UseCase<R,T> {
    suspend fun execute(param: R): T
}