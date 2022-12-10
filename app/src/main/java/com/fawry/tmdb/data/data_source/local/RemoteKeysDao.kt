package com.fawry.tmdb.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fawry.tmdb.data.model.RemoteKeys

@Dao
interface RemoteKeysDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(remoteKey: List<RemoteKeys>)

	@Query("SELECT * FROM remote_key WHERE movieId = :movieId")
	suspend fun getRemoteKeybyMovieId(movieId: Long): RemoteKeys?

	@Query("DELETE FROM remote_key")
	suspend fun clearRemoteKeys()
}
