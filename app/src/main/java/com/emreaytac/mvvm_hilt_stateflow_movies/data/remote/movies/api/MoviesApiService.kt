package com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movies.api

import com.emreaytac.mvvm_hilt_stateflow_movies.BuildConfig
import com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movies.MoviesResponse
import com.emreaytac.mvvm_hilt_stateflow_movies.data.utils.Constants.TR_CODE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesApiService {

    @GET("popular")
    suspend fun getMoviesList(@Query("language") language: String = TR_CODE,
                               @Query("api_key") apiKey: String = BuildConfig.API_KEY,
                               @Query("page") page: Int = 1,
    ) : Response<MoviesResponse>
}