package com.bluxtech.netfilm.core.di

import com.bluxtech.netfilm.core.data.MovieRepo
import com.bluxtech.netfilm.core.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(movieRepository: MovieRepo): MovieRepository
}