package com.emreaytac.mvvm_hilt_stateflow_movies.domain.utils

import com.emreaytac.mvvm_hilt_stateflow_movies.domain.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T, G> Flow<Resource<T>>.toNewResource(transform: (data: T) -> Resource.Success<G>): Flow<Resource<G>>{
    return map {res ->
        when(res){
            is Resource.Success -> {
                if (res.data != null){
                    return@map transform(res.data)
                } else {
                    return@map Resource.Empty<G>()
                }
            }
            is Resource.Error -> {
                return@map Resource.Error<G>(message = res.message)
            }
            is Resource.Loading ->{
                return@map Resource.Empty<G>()
            }
            is Resource.Empty -> {
                return@map Resource.Empty<G>()
            }
            else -> {
                return@map Resource.Empty<G>()
            }
        }
    }
}