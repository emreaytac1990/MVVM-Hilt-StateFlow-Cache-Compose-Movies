package com.emreaytac.mvvm_hilt_stateflow_movies.data.local.movies

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [MoviesEntity::class, MoviesRemoteKeys::class],
    version = 1
)
abstract class MoviesDatabase: RoomDatabase(){
    abstract val dao: MoviesDao
    abstract val remoteKeysDao: MoviesRemoteKeysDao
}