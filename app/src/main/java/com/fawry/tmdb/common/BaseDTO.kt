package com.fawry.tmdb.common

/**
 * Generic that allows transforming DTOs to Entities
 *
 * Implement this class in all DTOs
 * @see [ApiRequest]
 */
interface BaseDTO<T> {
	fun toEntity(): T
}
