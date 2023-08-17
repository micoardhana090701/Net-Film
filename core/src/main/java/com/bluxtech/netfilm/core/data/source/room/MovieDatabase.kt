package com.bluxtech.netfilm.core.data.source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bluxtech.netfilm.core.data.source.MovieEntity

@Database(entities = [MovieEntity::class], version = 3, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}