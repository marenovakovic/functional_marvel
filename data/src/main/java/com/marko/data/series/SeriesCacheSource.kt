package com.marko.data.series

import arrow.core.Either
import com.marko.data.entities.SeriesData
import com.marko.domain.entities.HeroId
import javax.inject.Inject

class SeriesCacheSource @Inject constructor(
	private val seriesCacheRepository: SeriesCacheRepository
) : SeriesDataSource {

	override suspend fun getSeriesForHero(heroId: HeroId): Either<Throwable, List<SeriesData>> =
		seriesCacheRepository.querySeriesForHero(heroId = heroId)
}