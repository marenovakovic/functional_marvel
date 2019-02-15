package com.marko.data.comics

import arrow.core.Either
import com.marko.data.entities.ComicsData

interface ComicsRemoteRepository {

	suspend fun fetchComics(): Either<Throwable, ComicsData>
}