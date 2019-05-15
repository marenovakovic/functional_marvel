package com.marko.remote.comics

import arrow.core.Either
import com.karumi.marvelapiclient.ComicApiClient
import com.karumi.marvelapiclient.model.ComicsQuery
import com.marko.data.comics.ComicsRemoteRepository
import com.marko.data.entities.ComicsData
import com.marko.domain.entities.HeroId
import com.marko.remote.extenstions.runSafe
import com.marko.remote.mappers.toData
import javax.inject.Inject

/**
 * [ComicsRemoteRepository] implementation. Communicates with API
 *
 * @param comicsApiClient [ComicApiClient] for fetching [ComicsData]
 */
class ComicsRemoteRepositoryImpl @Inject constructor(
	private val comicsApiClient: ComicApiClient
) : ComicsRemoteRepository {

	override suspend fun fetchComics(): Either<Throwable, ComicsData> =
		comicsApiClient.getAll(0, 25).runSafe().map { it.toData() }.toEither()

	override suspend fun fetchComicsForHero(heroId: HeroId): Either<Throwable, ComicsData> {
		val query = ComicsQuery.Builder.create().addCharacter(heroId.toInt()).build()
		return comicsApiClient.getAll(query).runSafe().map { it.toData() }.toEither()
	}
}