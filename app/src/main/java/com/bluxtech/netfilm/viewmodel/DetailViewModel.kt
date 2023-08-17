package com.bluxtech.netfilm.viewmodel

import androidx.lifecycle.ViewModel
import com.bluxtech.netfilm.core.domain.model.Movie
import com.bluxtech.netfilm.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteDetail(movie: Movie, newStatus:Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}