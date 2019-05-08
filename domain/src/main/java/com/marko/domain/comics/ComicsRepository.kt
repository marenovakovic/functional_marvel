package com.marko.domain.comics

import arrow.core.Either
import arrow.effects.IO
import com.marko.domain.entities.ComicsEntity
import com.marko.domain.entities.HeroId

/**
 * Data layer access point for [ComicsEntity]
 */
interface ComicsRepository {

	/**
	 * Get [ComicsEntity]
	 *
	 * @return [IO] with [Either] containing either result [ComicsEntity] or [Throwable] if something went wrong
	 */
	fun getComics(): IO<Either<Throwable, ComicsEntity>>

	/**
	 * Get [ComicsEntity] related to specific hero
	 *
	 * @param heroId [HeroId] for hero comics are related to
	 *
	 * @return [IO] with [Either] containing either result [ComicsEntity] or [Throwable] if something went wrong
	 */
	fun getComicsForHero(heroId: HeroId): IO<Either<Throwable, ComicsEntity>>
}