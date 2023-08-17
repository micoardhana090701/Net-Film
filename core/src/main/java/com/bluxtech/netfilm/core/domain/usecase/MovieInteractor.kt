package com.bluxtech.netfilm.core.domain.usecase

import com.bluxtech.netfilm.core.data.Resource
import com.bluxtech.netfilm.core.domain.model.Movie
import com.bluxtech.netfilm.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: MovieRepository) :
    MovieUseCase {

    override fun getAllMovie(key: String): Flow<Resource<List<Movie>>> =
        movieRepository.getAllMovies(key)

    override fun getAllMoviePlaying(key: String): Flow<Resource<List<Movie>>> =
        movieRepository.getAllMoviePlaying(key)

    override fun getAllMovieTopRated(key: String): Flow<Resource<List<Movie>>> =
        movieRepository.getAllMovieTopRated(key)

    override fun getFavoriteMovie(key: String): Flow<List<Movie>> =
        movieRepository.getFavoriteMovie(key)

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)

}