package com.bluxtech.netfilm.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bluxtech.netfilm.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val movieUseCase: MovieUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                DashboardViewModel(movieUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

}