package com.fawry.tmdb.infrastructure.local

import android.content.Context
import androidx.room.Room
import com.fawry.tmdb.data.data_source.local.MovieDao
import com.fawry.tmdb.data.data_source.local.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
	@Provides
	fun provideMovieDao(appDatabase: MovieDatabase): MovieDao {
		return appDatabase.movieDao()
	}

	@Provides
	fun provideRemoteKeysDao(appDatabase: MovieDatabase): RemoteKeysDao {
		return appDatabase.remoteKeysDao()
	}

	@Provides
	@Singleton
	fun provideAppDatabase(@ApplicationContext appContext: Context): MovieDatabase {
		return Room.databaseBuilder(
			appContext,
			MovieDatabase::class.java,
			"TMDB.db"
		).build()
	}
}
