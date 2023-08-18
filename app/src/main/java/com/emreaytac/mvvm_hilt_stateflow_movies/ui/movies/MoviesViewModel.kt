package com.emreaytac.mvvm_hilt_stateflow_movies.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.models.Movie
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.usecases.movies.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val useCase: MoviesUseCase): ViewModel() {

    private val PAGE_SIZE= 1
    private val _movies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val movies = _movies.asStateFlow()


    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.execute(MoviesUseCase.MoviesParams(page = PAGE_SIZE)).collect{
                _movies.value = it
            }
        }
    }

}