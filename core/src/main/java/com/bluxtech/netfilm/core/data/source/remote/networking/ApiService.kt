package com.bluxtech.netfilm.core.data.source.remote.networking

import com.bluxtech.netfilm.core.data.source.remote.response.DataMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("movie/popular")
    suspend fun getMovie(
        @Query("api_key") apiKey: String = "e631dbab50c8a96a2e570344aaf629ae",
    ): DataMovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = "e631dbab50c8a96a2e570344aaf629ae",
    ): DataMovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = "e631dbab50c8a96a2e570344aaf629ae",
    ): DataMovieResponse


}