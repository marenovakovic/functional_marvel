package com.marko.remote.series

import arrow.core.Either
import com.karumi.marvelapiclient.SeriesApiClient
import com.karumi.marvelapiclient.model.SeriesQuery
import com.marko.data.entities.SeriesData
import com.marko.data.series.SeriesRemoteRepository
import com.marko.domain.entities.HeroId
import com.marko.remote.extenstions.runSafe
import com.marko.remote.mappers.toData
import javax.inject.Inject

class SeriesRemoteRepositoryImpl @Inject constructor(
	private val seriesApiClient: SeriesApiClient
) : SeriesRemoteRepository {

	override suspend fun fetchSeriesForHero(heroId: HeroId): Either<Throwable, List<SeriesData>> {
		val query = SeriesQuery.Builder.create().addCharacter(heroId.toInt()).build()
		val result = seriesApiClient.getAll(query).runSafe().map { it.toData() }

		return result.toEither()
	}
}