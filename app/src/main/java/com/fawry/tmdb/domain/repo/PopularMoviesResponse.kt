package com.fawry.tmdb.domain.repo

import com.fawry.tmdb.domain.entity.Movie
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Paginated Response of Popular Movies endpoint
 */
data class PopularMoviesResponse(
	val page: Int? = null,
	val results: List<Movie>,
	val totalPages: Int,
	val totalResults: Int
) : Serializable

