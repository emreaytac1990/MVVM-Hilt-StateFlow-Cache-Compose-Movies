package com.emreaytac.mvvm_hilt_stateflow_movies.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.findNavController
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply{
            setContent {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val viewModel = hiltViewModel<MoviesViewModel>()
                    viewModel.getMovies()
                    val movies = viewModel.movies.collectAsLazyPagingItems()
                    MoviesScreen(movies = movies){ destination, argument ->
                        val bundle = Bundle()
                        bundle.putInt("movieID", argument)
                        findNavController().navigate(destination, bundle)
                    }
                }
            }
        }
    }

}