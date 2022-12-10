package com.fawry.tmdb.common

import android.util.Log
import retrofit2.Response

/**
 * Provides error handling
 */
abstract class ApiRequest {

	protected suspend fun <T: BaseDTO<R>, R> networkCall(call: suspend () -> Response<T>): Result<R> {

		try {
			val response = call()
			if (response.isSuccessful) {
				val body = response.body()
				val entity = body?.toEntity()
				if (entity != null) return Result.Success(entity)
			}
			return error(" ${response.code()} ${response.message()}")
		} catch (e: Exception) {
			return error(e.message ?: e.toString())
		}
	}

	private fun <T> error(message: String): Result<T> {
		Log.e("Network", message)
		return Result.Error("Network Failure: $message")
	}
}
