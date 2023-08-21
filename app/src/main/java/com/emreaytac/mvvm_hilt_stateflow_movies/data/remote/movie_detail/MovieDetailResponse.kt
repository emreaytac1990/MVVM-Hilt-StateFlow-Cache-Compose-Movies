package com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movie_detail

data class MovieDetailResponse(
    val adult               : Boolean?                       = null,
    val backdrop_path        : String?                        = null,
    //val belongs_to_collection : String?                        = null,
    val budget              : Int?                           = null,
    val genres              : List<Genres>                   = listOf(),
    val homepage            : String?                        = null,
    val id                  : Int?                           = null,
    val imdb_id              : String?                        = null,
    val original_language    : String?                        = null,
    val original_title       : String?                        = null,
    val overview            : String?                        = null,
    val popularity          : Double?                        = null,
    val poster_path          : String?                        = null,
    val production_companies : List<ProductionCompanies>      = listOf(),
    val production_countries : List<ProductionCountries>      = listOf(),
    val release_date         : String?                        = null,
    val revenue             : Int?                           = null,
    val runtime             : Int?                           = null,
    val spoken_languages     : List<SpokenLanguages>          = listOf(),
    val status              : String?                        = null,
    val tagline             : String?                        = null,
    val title               : String?                        = null,
    val video               : Boolean?                       = null,
    val vote_average         : Double?                        = null,
    val vote_count           : Int?                           = null

)


data class Genres (
    val id   : Int?    = null,
    val name : String? = null
)


data class ProductionCompanies (
    val id            : Int?    = null,
    val logo_path      : String? = null,
    val name          : String? = null,
    val origin_country : String? = null
)


data class ProductionCountries (
    val iso_3166_1 : String? = null,
    val name     : String? = null
)


data class SpokenLanguages (
    val english_name : String? = null,
    val iso_639_1     : String? = null,
    val name        : String? = null
)