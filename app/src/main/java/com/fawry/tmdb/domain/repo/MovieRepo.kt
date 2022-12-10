package com.fawry.tmdb.domain.repo


import com.fawry.tmdb.common.Result

interface MovieRepo{

	/**
	 * Returns movies, exposed as a flow that will emit every time we get more data from the network.
	 */
	suspend fun getMovies(page: Int): Result<PopularMoviesResponse>
}
