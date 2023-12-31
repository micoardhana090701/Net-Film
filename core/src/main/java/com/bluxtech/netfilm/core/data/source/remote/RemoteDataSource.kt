package com.bluxtech.netfilm.core.data.source.remote

import android.util.Log
import com.bluxtech.netfilm.core.data.source.remote.networking.ApiResponse
import com.bluxtech.netfilm.core.data.source.remote.networking.ApiService
import com.bluxtech.netfilm.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllMovie(key: String): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovie(key)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllMoviePlaying(key: String): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getNowPlayingMovies(key)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTopRatedMovies(key: String): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getTopRatedMovies(key)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}