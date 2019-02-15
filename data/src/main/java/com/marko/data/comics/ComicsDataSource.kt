package com.marko.data.comics

import arrow.core.Either
import com.marko.data.entities.ComicsData

interface ComicsDataSource {

	suspend fun getComics(): Either<Throwable, ComicsData>
}