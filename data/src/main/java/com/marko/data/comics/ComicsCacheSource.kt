package com.marko.data.comics

import arrow.core.Either
import com.marko.data.entities.ComicsData
import javax.inject.Inject

class ComicsCacheSource @Inject constructor(
	private val comicsCacheRepository: ComicsCacheRepository
) : ComicsDataSource {

	override suspend fun getComics(): Either<Throwable, ComicsData> =
		comicsCacheRepository.queryComics()
}