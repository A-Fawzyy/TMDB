package com.fawry.tmdb.data.data_source.remote

import com.fawry.tmdb.common.ApiRequest
import com.fawry.tmdb.common.Result
import com.fawry.tmdb.data.data_source.remote.service.MoviesService
import com.fawry.tmdb.data.model.PopularMoviesResponseDTO
import com.fawry.tmdb.domain.repo.PopularMoviesResponse
import javax.inject.Inject

/**
 * Works with [MoviesService] to retrieve movie data
 */
class MovieRemoteDataSource @Inject constructor(
	private val service: MoviesService
) : ApiRequest() {
	/**
	 * Returns a paginated list of movies
	 *
	 */
	suspend fun fetchMoviesList(page: Int): Result<PopularMoviesResponse> =
		networkCall { service.getMovies(page) }
}
