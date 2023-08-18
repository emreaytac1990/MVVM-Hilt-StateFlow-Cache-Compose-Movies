package com.emreaytac.mvvm_hilt_stateflow_movies.data.utils

import com.emreaytac.mvvm_hilt_stateflow_movies.BuildConfig
import com.emreaytac.mvvm_hilt_stateflow_movies.data.local.movies.MoviesEntity
import com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movies.Results
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.models.Movie

fun Results.toMoviesEntity(): MoviesEntity{
    return MoviesEntity(
        id = id!!,
        adult = adult,
        originalLanguage = original_language,
        originalTitle = original_title,
        overview = overview,
        popularity = popularity,
        posterPath = poster_path,
        releaseDate = release_date,
        title = title,
        voteAverage = vote_average,
        voteCount = vote_count
    )
}

fun MoviesEntity.toMovies(): Movie {
    return Movie(
        id = id,
        adult = adult,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun String.toImageUrl(): String{
    return BuildConfig.IMAGE_BASE_URL + this
}