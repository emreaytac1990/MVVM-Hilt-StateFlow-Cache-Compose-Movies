package com.emreaytac.mvvm_hilt_stateflow_movies.ui.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.Resource
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.models.MovieDetail
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.usecases.movie_detail.MovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val useCase: MovieDetailUseCase): ViewModel() {

    private val _movieDetail = MutableStateFlow<MovieDetailEvents>(MovieDetailEvents.ShowEmpty)
    val movies = _movieDetail.asStateFlow()

    private fun onEvent(event: MovieDetailEvents){
        _movieDetail.value = event
    }

    fun getMovieDetail(id: Int){
        onEvent(MovieDetailEvents.ShowLoading)

        viewModelScope.launch(Dispatchers.IO) {
            useCase.execute(MovieDetailUseCase.MovieDetailParams(id)).collectLatest {
                when(it){
                    is Resource.Success -> {
                        it.data?.let { res ->
                            onEvent(MovieDetailEvents.ShowMovieDetail(res))
                        }
                    }
                    is Resource.Empty -> {
                        onEvent(MovieDetailEvents.ShowEmpty)
                    }
                    is Resource.Error -> {
                        onEvent(MovieDetailEvents.ShowToastMessage(it.message))
                    }
                    is Resource.Loading -> {
                        onEvent(MovieDetailEvents.ShowLoading)
                    }
                }
            }
        }
    }
}