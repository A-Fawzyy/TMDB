package com.fawry.tmdb.data.data_source.local

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import javax.inject.Singleton

@Singleton
class SharedPrefsClient(@ApplicationContext private val context: Context) {
	private val sharedPrefs =
		PreferenceManager.getDefaultSharedPreferences(context)

	/** Saves the time of the last remote sync process for the movies */
	fun saveLastRemoteSyncTime(time: Long) {
		sharedPrefs.edit().putLong(LAST_REMOTE_SYNC_TIME_KEY, time).apply()
	}

	/** Returns the time of the last remote sync process for the movies */
	fun getLastRemoteSyncTime() : Long =
		sharedPrefs.getLong(LAST_REMOTE_SYNC_TIME_KEY, 0L)

	companion object {
		/** Key for storing the time of the last remote sync process for the movies */
		const val LAST_REMOTE_SYNC_TIME_KEY = "last-update-time"
	}
}
