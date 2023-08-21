package com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movies

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.emreaytac.mvvm_hilt_stateflow_movies.data.local.movies.MoviesDatabase
import com.emreaytac.mvvm_hilt_stateflow_movies.data.local.movies.MoviesEntity
import com.emreaytac.mvvm_hilt_stateflow_movies.data.local.movies.MoviesRemoteKeys
import com.emreaytac.mvvm_hilt_stateflow_movies.data.remote.movies.api.MoviesApiService
import com.emreaytac.mvvm_hilt_stateflow_movies.data.utils.toMoviesEntity
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject



@OptIn(ExperimentalPagingApi::class)
class MoviesRemoteMediator @Inject constructor(
    private val apiService: MoviesApiService,
    private val moviesDatabase: MoviesDatabase
): RemoteMediator<Int, MoviesEntity>() {

    // Its caching timeout which check cache data is out of date
    // decide whether to trigger remote refresh
    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)

        return if (System.currentTimeMillis() - (moviesDatabase.remoteKeysDao.getCreationTime() ?: 0) < cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MoviesEntity>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {

            val apiResponse = apiService.getMoviesList(page = page)

            val movies = apiResponse.body()?.results

            val endOfPaginationReached = movies.isNullOrEmpty()

            moviesDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    moviesDatabase.remoteKeysDao.clearRemoteKeys()
                    moviesDatabase.dao.clearAll()
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = movies?.map {
                    MoviesRemoteKeys(
                        prevKey = prevKey,
                        currentPage = page,
                        nextKey = nextKey
                    )
                }

                remoteKeys?.let {
                    moviesDatabase.remoteKeysDao.insertAll(it)
                }
                val moviesEntities = movies?.map { it.toMoviesEntity() }
                moviesEntities?.let { moviesDatabase.dao.upsertAll(it) }
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            return MediatorResult.Error(error)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MoviesEntity>): MoviesRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                moviesDatabase.remoteKeysDao.getRemoteKeyByMovieID(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MoviesEntity>): MoviesRemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { movie ->
            moviesDatabase.remoteKeysDao.getRemoteKeyByMovieID(movie.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MoviesEntity>): MoviesRemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { movie ->
            moviesDatabase.remoteKeysDao.getRemoteKeyByMovieID(movie.id)
        }
    }
}