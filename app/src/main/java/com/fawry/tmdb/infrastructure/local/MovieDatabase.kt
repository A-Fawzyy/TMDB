package com.fawry.tmdb.infrastructure.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fawry.tmdb.data.data_source.local.MovieDao
import com.fawry.tmdb.data.data_source.local.RemoteKeysDao
import com.fawry.tmdb.data.model.RemoteKeys
import com.fawry.tmdb.domain.entity.Movie

@Database(
	entities = [Movie::class, RemoteKeys::class],
	version = 1
)
abstract class MovieDatabase : RoomDatabase() {
	abstract fun movieDao(): MovieDao
	abstract fun remoteKeysDao(): RemoteKeysDao
}
