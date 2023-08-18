package com.emreaytac.mvvm_hilt_stateflow_movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.emreaytac.mvvm_hilt_stateflow_movies.ui.movies.MoviesScreen
import com.emreaytac.mvvm_hilt_stateflow_movies.ui.movies.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                val viewModel = hiltViewModel<MoviesViewModel>()
                viewModel.getMovies()
                val movies = viewModel.movies.collectAsLazyPagingItems()
                MoviesScreen(movies = movies)
            }
        }
    }
}