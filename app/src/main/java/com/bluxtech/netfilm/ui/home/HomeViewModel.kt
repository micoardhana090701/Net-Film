package com.bluxtech.netfilm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bluxtech.netfilm.BuildConfig
import com.bluxtech.netfilm.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie(BuildConfig.API_KEY).asLiveData()
    val moviePlaying = movieUseCase.getAllMoviePlaying(BuildConfig.API_KEY).asLiveData()
    val movieTopRated = movieUseCase.getAllMovieTopRated(BuildConfig.API_KEY).asLiveData()
}