package com.fawry.tmdb.data.repo

import com.fawry.tmdb.common.Result
import com.fawry.tmdb.data.data_source.remote.MovieRemoteDataSource
import com.fawry.tmdb.domain.repo.MovieRepo
import com.fawry.tmdb.domain.repo.PopularMoviesResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMovieRepo @Inject constructor(
	private val dataSource: MovieRemoteDataSource
) : MovieRepo {
	override suspend fun getMovies(page: Int): Result<PopularMoviesResponse> = dataSource.fetchMoviesList(page)

}






