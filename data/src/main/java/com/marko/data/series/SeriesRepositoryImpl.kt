package com.marko.data.series

import arrow.core.Either
import arrow.core.getOrElse
import arrow.effects.IO
import arrow.effects.extensions.io.fx.fx
import com.marko.data.mappers.toEntity
import com.marko.domain.entities.HeroId
import com.marko.domain.entities.SeriesEntity
import com.marko.domain.series.SeriesRepository
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
	private val remoteSource: SeriesDataSource,
	private val cacheSource: SeriesDataSource
) : SeriesRepository {

	override fun getSeriesForHero(heroId: HeroId): IO<Either<Throwable, List<SeriesEntity>>> = fx {
		val cachedSeries = ! effect { cacheSource.getSeriesForHero(heroId = heroId) }

		if (cachedSeries.isLeft() || cachedSeries.getOrElse { emptyList() }.isEmpty())
			! effect { remoteSource.getSeriesForHero(heroId = heroId) }
		else cachedSeries
	}
		.map { result -> result.map { series -> series.toEntity() } }
}