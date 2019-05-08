package com.marko.domain.usecases

import arrow.core.Either
import arrow.effects.IO
import com.marko.domain.entities.HeroId
import com.marko.domain.entities.SeriesEntity
import com.marko.domain.series.SeriesRepository
import javax.inject.Inject

/**
 * Executes business logic for fetching [SeriesEntity] related to specific hero
 *
 * @param seriesRepository [SeriesRepository]
 */
class FetchSeriesForHero @Inject constructor(
	private val seriesRepository: SeriesRepository
) : UseCase<HeroId, List<SeriesEntity>>() {

	override fun execute(parameters: HeroId): IO<Either<Throwable, List<SeriesEntity>>> =
		seriesRepository.getSeriesForHero(heroId = parameters)
}