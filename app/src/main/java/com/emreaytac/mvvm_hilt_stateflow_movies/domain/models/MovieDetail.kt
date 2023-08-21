package com.emreaytac.mvvm_hilt_stateflow_movies.domain.models

import com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movie_detail.Genres
import com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movie_detail.ProductionCompanies
import com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movie_detail.ProductionCountries
import com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movie_detail.SpokenLanguages

data class MovieDetail(
    val adult               : Boolean?                       = null,
    val backdropPath        : String?                        = null,
    val budget              : Int?                           = null,
    val genres              : List<Genres>                   = listOf(),
    val homepage            : String?                        = null,
    val id                  : Int?                           = null,
    val imdbId              : String?                        = null,
    val originalLanguage    : String?                        = null,
    val originalTitle       : String?                        = null,
    val overview            : String?                        = null,
    val popularity          : Double?                        = null,
    val posterPath          : String?                        = null,
    val releaseDate         : String?                        = null,
    val status              : String?                        = null,
    val tagline             : String?                        = null,
    val title               : String?                        = null,
    val voteAverage         : Double?                        = null,
    val voteCount           : Int?                           = null
)
