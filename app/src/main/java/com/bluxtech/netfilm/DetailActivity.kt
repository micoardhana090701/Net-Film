package com.bluxtech.netfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bluxtech.netfilm.core.domain.model.Movie
import com.bluxtech.netfilm.databinding.ActivityDetailBinding
import com.bluxtech.netfilm.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        if (detailMovie != null){
            showDetailMovie(detailMovie)
        }


    }

    private fun showDetailMovie(detailMovie: Movie) {
        val ivMovieDetail = binding.ivMovieDetail
        detailMovie.posterPath.let {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/original$it")
                .centerCrop()
                .into(ivMovieDetail)
        }
        binding.tvTitle.setText(detailMovie.title)
        binding.tvDate.setText(detailMovie.releaseDate)
        binding.tvRating.setText(detailMovie.voteAverage.toString())
        binding.tvCountRating.setText(detailMovie.voteCount.toString())
        binding.tvOverview.setText(detailMovie.overview)
        var favorite = detailMovie.isFavorite
        setStatusFavorite(favorite)
        binding.btnSave.setOnClickListener {
            favorite = !favorite
            detailViewModel.setFavoriteDetail(detailMovie, favorite)
            setStatusFavorite(favorite)
            if (favorite){
                Toast.makeText(this, "Film has been saved to favorite", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, "Film has been deleted from favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun setStatusFavorite(favorite: Boolean) {
        if (favorite) {
            binding.ivSave.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_bookmark_24))
        } else {
            binding.ivSave.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.outline_bookmark_border_24))
        }
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}