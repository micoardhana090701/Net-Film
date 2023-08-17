package com.bluxtech.netfilm.favorite.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluxtech.netfilm.DetailActivity
import com.bluxtech.netfilm.core.ui.MovieAdapter
import com.bluxtech.netfilm.di.FavoriteModuleDependencies
import com.bluxtech.netfilm.favorite.databinding.FragmentDashboardBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class DashboardFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val dashboardViewModel: DashboardViewModel by viewModels{
        factory
    }

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(EntryPointAccessors.fromApplication(
                context?.applicationContext!!,
                FavoriteModuleDependencies::class.java
            ))
            .build()
            .inject(this)
        super.onViewCreated(view, savedInstanceState)
        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        dashboardViewModel.favoriteMovie.observe(viewLifecycleOwner) { dataMovie ->
            movieAdapter.setData(dataMovie)
        }

        with(binding.rvFavorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}