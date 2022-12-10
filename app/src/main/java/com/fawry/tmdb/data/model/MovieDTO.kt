package com.fawry.tmdb.data.model

import com.fawry.tmdb.common.BaseDTO
import com.fawry.tmdb.domain.entity.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class MovieDTO (
	@field:SerializedName("id")
	val id: Long,
	@field:SerializedName("overview")
	val overview: String,
	@field:SerializedName("backdrop_path")
	val backdropPath: String,
	@field:SerializedName("poster_ath")
	val posterPath: String,
	@field:SerializedName("title")
	val title: String,
	@Expose
	@field:SerializedName("cachedAt")
	val cachedAt: Long = Calendar.getInstance().timeInMillis,
) : Serializable, BaseDTO<Movie> {

	override fun toEntity(): Movie = toEntity()
}
