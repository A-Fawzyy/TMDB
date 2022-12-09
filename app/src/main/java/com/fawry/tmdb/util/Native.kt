package com.fawry.tmdb.util

object Native {

		init {
			System.loadLibrary("native-lib")
		}

		external fun apiKey(): String
}
