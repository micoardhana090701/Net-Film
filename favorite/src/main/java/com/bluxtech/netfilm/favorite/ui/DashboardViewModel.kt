package com.bluxtech.netfilm.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bluxtech.netfilm.BuildConfig
import com.bluxtech.netfilm.core.domain.usecase.MovieUseCase

class DashboardViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie(BuildConfig.API_KEY).asLiveData()
}