package com.bluxtech.netfilm.core.domain.usecase

import com.bluxtech.netfilm.core.data.Resource
import com.bluxtech.netfilm.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(key: String): Flow<Resource<List<Movie>>>
    fun getAllMoviePlaying(key: String): Flow<Resource<List<Movie>>>
    fun getAllMovieTopRated(key: String): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(key: String): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}