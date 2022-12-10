package com.fawry.tmdb.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Movie (
	@PrimaryKey
	val id: Long,
	val overview: String,
	val backdropPath: String,
	val posterPath: String,
	val title: String,
	val cachedAt: Long,
) : Serializable
