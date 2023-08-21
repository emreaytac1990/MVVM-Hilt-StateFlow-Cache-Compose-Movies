package com.emreaytac.mvvm_hilt_stateflow_movies.ui.movie_detail

import com.emreaytac.mvvm_hilt_stateflow_movies.domain.models.MovieDetail

sealed interface MovieDetailEvents {
    data class ShowMovieDetail(val data: MovieDetail): MovieDetailEvents

    object ShowEmpty : MovieDetailEvents

    object ShowLoading : MovieDetailEvents

    data class ShowToastMessage(val message: String): MovieDetailEvents
}