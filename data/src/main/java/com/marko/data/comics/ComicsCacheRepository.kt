package com.marko.data.comics

import arrow.core.Either
import com.marko.data.entities.ComicsData

interface ComicsCacheRepository {

	suspend fun queryComics(): Either<Throwable, ComicsData>
}