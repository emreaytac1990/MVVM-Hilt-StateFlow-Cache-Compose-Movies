package com.emreaytac.mvvm_hilt_stateflow_movies.domain.models

data class Movie (
    val adult            : Boolean?,
    val id               : Int    ,
    val remoteId         : Int     ,
    val originalLanguage : String? ,
    val originalTitle    : String? ,
    val overview         : String? ,
    val popularity       : Double? ,
    val posterPath       : String? ,
    val releaseDate      : String? ,
    val title            : String? ,
    val voteAverage      : Double? ,
    val voteCount        : Int?,
)