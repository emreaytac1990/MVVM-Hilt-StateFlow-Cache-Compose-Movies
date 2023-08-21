package com.emreaytac.mvvm_hilt_stateflow_movies.domain

sealed interface Resource<T> {

    class Success<T>(val data: T?): Resource<T>

    class Loading<T> : Resource<T>

    class Error<T>(val message: String): Resource<T>

    class Empty<T>: Resource<T>


}