package com.marko.data.series

import arrow.core.Either
import com.marko.data.entities.SeriesData
import com.marko.domain.entities.HeroId
import javax.inject.Inject

class SeriesRemoteSource @Inject constructor(
	private val seriesRemoteRepository: SeriesRemoteRepository
) : SeriesDataSource {

	override suspend fun getSeriesForHero(heroId: HeroId): Either<Throwable, List<SeriesData>> =
		seriesRemoteRepository.fetchSeriesForHero(heroId = heroId)
}