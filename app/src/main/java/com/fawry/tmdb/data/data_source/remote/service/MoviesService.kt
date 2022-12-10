package com.fawry.tmdb.data.data_source.remote.service

import com.fawry.tmdb.data.model.MovieDTO
import com.fawry.tmdb.data.model.PopularMoviesResponseDTO
import com.fawry.tmdb.util.Native
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * The MovieDB Remote DataSource
 */
interface MoviesService {

	/**
	 * Returns Movies list for the selected page
	 *
	 * Items per page is always 20 till further customization by TMDB
	 */
	@GET("${MOVIE_PATH}popular")
	suspend fun getMovies(
		@Query("page") page: Int,
		@Query("apiKey") apiKey: String? = Native.apiKey()
	) : Response<PopularMoviesResponseDTO>

	/**
	 * Returns Details of the given [movieId]
	 */
	@GET("$MOVIE_PATH{movieId}")
	suspend fun getMovieDetails(
		@Path("movieId") movieId: Long,
		@Query("apiKey") apiKey: String? = Native.apiKey()
	) : Response<MovieDTO>

	companion object {
		const val MOVIE_PATH = "3/movie/"
	}
}
