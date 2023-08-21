package com.emreaytac.mvvm_hilt_stateflow_movies.ui.movie_detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.emreaytac.mvvm_hilt_stateflow_movies.R
import com.emreaytac.mvvm_hilt_stateflow_movies.data.utils.toImageUrl
import com.emreaytac.mvvm_hilt_stateflow_movies.databinding.FragmentMovieDetailBinding
import com.emreaytac.mvvm_hilt_stateflow_movies.domain.models.MovieDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val ARG_MOVIE_ID = "movieID"

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var movieId: Int? = null
    private val viewModel: MovieDetailViewModel by viewModels()
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(ARG_MOVIE_ID)
        }


        lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED){
                viewModel.movies.collect{event ->
                    when(event){
                        is MovieDetailEvents.ShowMovieDetail -> {
                            buildUI(event.data)
                        }
                        is MovieDetailEvents.ShowEmpty -> {}
                        is MovieDetailEvents.ShowLoading -> {}
                        is MovieDetailEvents.ShowToastMessage -> {
                            Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun buildUI(data: MovieDetail){
        binding.tagline.text = data.tagline ?: ""
        var genres = ""
        data.genres.forEach{ x -> x.name?.let { genres += if(x == data.genres.last()) x.name + "," else x.name + "," } }
        binding.genres.text = genres
        binding.status.text = data.status ?: ""
        binding.releaseDate.text = data.releaseDate ?: ""
        binding.voteAverage.text = if (data.voteAverage != null) data.voteAverage.toString() else ""
        binding.voteCount.text = if (data.voteCount != null) data.voteCount.toString() else ""
        binding.overwiew.text = data.overview
        binding.toolbarLayout.title = data.title ?: ""
        binding.toolbar.title = data.title ?: ""

        data.posterPath?.let {
            val url = it.toImageUrl()
            Glide.with(this).load(url).into(binding.testImg)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val act = activity as AppCompatActivity
        act.setSupportActionBar(binding.toolbar)
        act.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        act.supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbarLayout.isTitleEnabled = false
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
    override fun onStart() {
        super.onStart()
        movieId?.let {
            viewModel.getMovieDetail(it)
        }
    }

}