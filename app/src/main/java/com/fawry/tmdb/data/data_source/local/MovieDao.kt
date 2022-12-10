package com.fawry.tmdb.data.data_source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fawry.tmdb.domain.entity.Movie

@Dao
interface MovieDao {
	/**
	 * Gets Movies from local DB with pagination
	 */
	@Query("SELECT * FROM movie")
	fun retrievePopularMovies() : PagingSource<Int, Movie>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAll(movies: List<Movie>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(movie: Movie)

	@Query("DELETE FROM movie")
	suspend fun clearMovies()
}
