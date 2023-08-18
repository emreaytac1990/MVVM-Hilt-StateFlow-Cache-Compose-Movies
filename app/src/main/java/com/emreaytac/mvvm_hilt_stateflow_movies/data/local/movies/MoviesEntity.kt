package com.emreaytac.mvvm_hilt_stateflow_movies.data.local.movies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MoviesEntity (
    val adult            : Boolean?,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "movie_id")
    val id               : Int    ,
    val originalLanguage : String? ,
    val originalTitle    : String? ,
    val overview         : String? ,
    val popularity       : Double? ,
    val posterPath       : String? ,
    val releaseDate      : String? ,
    val title            : String? ,
    val voteAverage      : Double? ,
    val voteCount        : Int?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)

@Entity
data class MoviesRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "movie_id")
    val id: Int,
    val prevKey: Int?,
    val currentPage: Int,
    val nextKey: Int?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)