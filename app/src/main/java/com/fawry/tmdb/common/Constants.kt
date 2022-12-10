package com.fawry.tmdb.common

/**
 * Singleton holding constants
 *
 * held like that in order to be properly obfuscated later on
 */
object Constants {
	// this is placed here for simplicity as this url is public
	const val BASE_URL = "https://api.themoviedb.org"

	/**
	 * TheMovieDatabase items per page
	 *
	 * currently TMDB only returns 20 items with no way of changing it
	 */
	const val PAGE_SIZE = 20
	const val FIRST_PAGE_KEY = 1
}
