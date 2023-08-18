package com.emreaytac.mvvm_hilt_stateflow_movies.data.local.movies

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MoviesDao {
    @Upsert
    suspend fun upsertAll(movies: List<MoviesEntity>)

    @Query("Select * from moviesentity")
    fun pagingSource(): PagingSource<Int, MoviesEntity>

    @Query("DELETE FROM moviesentity")
    suspend fun clearAll()
}

@Dao
interface MoviesRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<MoviesRemoteKeys>)

    @Query("Select * From moviesremotekeys Where movie_id = :id")
    suspend fun getRemoteKeyByMovieID(id: Int): MoviesRemoteKeys?

    @Query("Delete From moviesremotekeys")
    suspend fun clearRemoteKeys()

    @Query("Select created_at From moviesremotekeys Order By created_at DESC LIMIT 1")
    suspend fun getCreationTime(): Long?
}