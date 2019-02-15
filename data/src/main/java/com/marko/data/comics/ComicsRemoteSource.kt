package com.marko.data.comics

import arrow.core.Either
import com.marko.data.entities.ComicsData
import javax.inject.Inject

class ComicsRemoteSource @Inject constructor(
	private val comicsRemoteRepository: ComicsRemoteRepository
) : ComicsDataSource {

	override suspend fun getComics(): Either<Throwable, ComicsData> =
		comicsRemoteRepository.fetchComics()
}