package com.marko.domain.series

import arrow.core.Either
import arrow.effects.IO
import com.marko.domain.entities.HeroId
import com.marko.domain.entities.SeriesEntity

/**
 * Data layer access point for [SeriesEntity]
 */
interface SeriesRepository {

	/**
	 * Get [SeriesEntity] [List] related to specific hero
	 *
	 * @param heroId [HeroId] for hero comics are related to
	 *
	 * @return [IO] with [Either] containing either result [SeriesEntity] [List] or [Throwable] if something went wrong
	 */
	fun getSeriesForHero(heroId: HeroId): IO<Either<Throwable, List<SeriesEntity>>>
}