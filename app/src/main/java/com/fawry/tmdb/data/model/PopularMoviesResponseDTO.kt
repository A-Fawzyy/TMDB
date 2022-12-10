package com.fawry.tmdb.data.model

import com.fawry.tmdb.common.BaseDTO
import com.fawry.tmdb.domain.repo.PopularMoviesResponse
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Paginated Response of Popular Movies endpoint
 */
data class PopularMoviesResponseDTO (
	@SerializedName("page")
	val page: Int? = null,
	@SerializedName("results")
	val results: List<MovieDTO>,
	@SerializedName("total_pages")
	val totalPages: Int,
	@SerializedName("total_results")
	val totalResults: Int
) : Serializable, BaseDTO<PopularMoviesResponse> {

	override fun toEntity(): PopularMoviesResponse =
		toEntity()
}
