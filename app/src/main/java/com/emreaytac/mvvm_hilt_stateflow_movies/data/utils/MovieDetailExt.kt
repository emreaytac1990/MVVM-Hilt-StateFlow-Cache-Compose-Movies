package com.emreaytac.mvvm_hilt_stateflow_movies.data.utils

import com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movie_detail.MovieDetailResponse
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.models.MovieDetail

fun MovieDetailResponse.toMovieDetail(): MovieDetail{
    return MovieDetail(
        adult = adult,
        backdropPath = backdrop_path,
        budget = budget,
        genres = genres,
        homepage = homepage,
        id = id,
        imdbId = imdb_id,
        originalLanguage = original_language,
        originalTitle = original_title,
        overview = overview,
        popularity = popularity,
        posterPath = poster_path,
        releaseDate = release_date,
        status = status,
        tagline = tagline,
        title = title,
        voteAverage = vote_average,
        voteCount = vote_count
    )
}