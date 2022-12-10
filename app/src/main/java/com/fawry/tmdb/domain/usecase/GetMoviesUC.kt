package com.fawry.tmdb.domain.usecase

import com.fawry.tmdb.domain.repo.MovieRepo
import javax.inject.Inject

class GetMoviesUC @Inject constructor(private val repo: MovieRepo) {

}
