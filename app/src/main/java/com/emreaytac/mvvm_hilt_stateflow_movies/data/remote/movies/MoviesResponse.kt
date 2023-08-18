package com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movies

import com.squareup.moshi.Json

data class MoviesResponse(
    @Json(name = "page") val page         : Int?               = null,
    @Json(name = "results") val results      : List<Results> = listOf(),
    @Json(name = "total_pages") val total_pages   : Int?               = null,
    @Json(name = "total_results") val total_results : Int?               = null
)

data class Results (
    @Json(name = "adult") val adult            : Boolean?       = null,
    @Json(name = "backdrop_path") val backdrop_path     : String?        = null,
    @Json(name = "genre_ids") val genre_ids         : List<Int> = listOf(),
    @Json(name = "id") val id               : Int?           = null,
    @Json(name = "original_language") val original_language : String?        = null,
    @Json(name = "original_title") val original_title    : String?        = null,
    @Json(name = "overview") val overview         : String?        = null,
    @Json(name = "popularity") val popularity       : Double?        = null,
    @Json(name = "poster_path") val poster_path       : String?        = null,
    @Json(name = "release_date") val release_date      : String?        = null,
    @Json(name = "title") val title            : String?        = null,
    @Json(name = "video") val video            : Boolean?       = null,
    @Json(name = "vote_average") val vote_average      : Double?        = null,
    @Json(name = "vote_count") val vote_count        : Int?           = null
)
