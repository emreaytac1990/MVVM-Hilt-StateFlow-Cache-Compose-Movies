package com.emreaytac.mvvm_hilt_stateflow_movies.ui.movies

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.emreaytac.mvvm_hilt_stateflow_movies.R
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.models.Movie

@Composable
fun MoviesScreen(
    movies: LazyPagingItems<Movie>,
    onNavigate: (Int, Int) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = movies.loadState){
        if (movies.loadState.refresh is LoadState.Error){
            val err = (movies.loadState.refresh as LoadState.Error).error.message ?: ""
            Toast.makeText(context, "Error: $err", Toast.LENGTH_LONG).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if(movies.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = movies.itemCount,
                    key = movies.itemKey { it.id }
                ){
                    val movie = movies[it]
                    if (movie != null){
                        MovieItem(
                            movie = movie,
                            modifier = Modifier.fillMaxWidth().clickable {
                                onNavigate(R.id.action_moviesFragment_to_movieDetailFragment, movie.remoteId)
                                //Toast.makeText(context, "Tıklandı", Toast.LENGTH_LONG).show()
                            }
                        )
                    }
                }

                item {
                    if(movies.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}