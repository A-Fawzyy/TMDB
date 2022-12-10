package com.fawry.tmdb.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "remote_key")
data class RemoteKeys (
	@PrimaryKey
	val movieId: Long,
	val prevKey: Int?,
	val nextKey: Int?
) : Serializable
