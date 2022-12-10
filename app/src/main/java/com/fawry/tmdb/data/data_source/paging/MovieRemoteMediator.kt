package com.fawry.tmdb.data.data_source.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.fawry.tmdb.common.Result
import com.fawry.tmdb.data.data_source.local.MovieDao
import com.fawry.tmdb.data.data_source.local.RemoteKeysDao
import com.fawry.tmdb.data.data_source.local.SharedPrefsClient
import com.fawry.tmdb.data.model.RemoteKeys
import com.fawry.tmdb.domain.entity.Movie
import com.fawry.tmdb.domain.repo.MovieRepo
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class BlogRemoteMediator @Inject constructor(
	private val initialPage: Int = 1,
	private val movieRepo: MovieRepo,
	private val sharedPrefsClient: SharedPrefsClient,
	private val keysDao: RemoteKeysDao,
	private val movieDao: MovieDao
) : RemoteMediator<Int, Movie>() {

	override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {

		return try {

			val page: Int = when (loadType) {
				LoadType.APPEND -> {
					val remoteKeys = getLastKey(state)
					remoteKeys?.nextKey ?: return MediatorResult.Success(true)
				}
				LoadType.PREPEND -> {
					return MediatorResult.Success(true)
				}
				LoadType.REFRESH -> {
					val remoteKeys = getClosetKey(state)
					remoteKeys?.nextKey?.minus(1) ?: initialPage

				}
			}

			val lastMoviesSyncTime = sharedPrefsClient.getLastRemoteSyncTime()

			val response =
				movieRepo.getMovies(page = page)

			when (response) {
				is Result.Success -> {
					val endOfPagination = response.data.results.size < state.config.pageSize
					val body = response.data.results

					if (loadType == LoadType.REFRESH) {
						keysDao.clearRemoteKeys()
						movieDao.clearMovies()
					}

					val prev = if (page == initialPage) initialPage else page - 1
					val next = if (endOfPagination) null else page + 1

					val list = body.map {
						RemoteKeys(movieId = it.id, prevKey = prev, nextKey = next)
					}
					// Todo: Store time+
					list.let { keysDao.insertAll(list) }
					body.let { movieDao.insertAll(body) }


				}
				is Result.Error -> {
					MediatorResult.Error(Exception())

				}

			}

			if (response is Result.Success) {
				val endOfPagination = response.data.results.size < state.config.pageSize
				if (endOfPagination) {
					MediatorResult.Success(true)

				} else {
					MediatorResult.Success(false)
				}
			} else {
				MediatorResult.Success(true)
			}


		} catch (E: Exception) {

			MediatorResult.Error(E)

		}
	}

	private suspend fun getLastKey(state: PagingState<Int, Movie>): RemoteKeys? {
		return state.lastItemOrNull()?.let {
			keysDao.getRemoteKeybyMovieId(it.id)
		}
	}

	private suspend fun getClosetKey(state: PagingState<Int, Movie>): RemoteKeys? {
		return state.anchorPosition?.let {
			state.closestItemToPosition(it)?.let { movie ->
				keysDao.getRemoteKeybyMovieId(movie.id)
			}
		}
	}

}
