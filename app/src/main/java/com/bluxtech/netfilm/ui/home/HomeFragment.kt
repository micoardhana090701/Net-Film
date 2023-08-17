package com.bluxtech.netfilm.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bluxtech.netfilm.DetailActivity
import com.bluxtech.netfilm.core.data.Resource
import com.bluxtech.netfilm.core.domain.model.Movie
import com.bluxtech.netfilm.core.ui.MovieAdapter
import com.bluxtech.netfilm.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val movieAdapter = MovieAdapter()

            movieAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, it)
                startActivity(intent)
            }

            homeViewModel.movie.observe(viewLifecycleOwner) { movie ->
                movieData(movie, movieAdapter)
            }

            homeViewModel.moviePlaying.observe(viewLifecycleOwner) { movie ->
                movieData(movie, movieAdapter)
            }

            homeViewModel.movieTopRated.observe(viewLifecycleOwner) { movie ->
                movieData(movie, movieAdapter)
            }

            with(binding.rvMovie) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
    private fun movieData(
        movie: Resource<List<Movie>>,
        movieAdapter: MovieAdapter,
    ) {
        when (movie) {
            is Resource.Loading -> {}
            is Resource.Success -> {
                movieAdapter.setData(movie.data)
            }
            is Resource.Error -> {
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}