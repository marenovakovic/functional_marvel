package com.marko.remote.comics

import arrow.core.Either
import com.karumi.marvelapiclient.ComicApiClient
import com.marko.data.comics.ComicsRemoteRepository
import com.marko.data.entities.ComicsData
import javax.inject.Inject

class ComicsRemoteRepositoryImpl @Inject constructor(
	private val comicsApiClient: ComicApiClient
) : ComicsRemoteRepository {

	override suspend fun fetchComics(): Either<Throwable, ComicsData> {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}