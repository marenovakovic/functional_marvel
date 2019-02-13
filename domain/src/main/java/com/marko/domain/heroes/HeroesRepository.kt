package com.marko.domain.heroes

import arrow.core.Either
import arrow.effects.IO
import com.marko.domain.entities.HeroEntity
import com.marko.domain.entities.HeroId
import com.marko.domain.entities.HeroesEntity

/**
 * Data layer access point for [HeroesEntity]
 */
interface HeroesRepository {

	/**
	 * Get [HeroesEntity]
	 *
	 * @return [IO] with [Either] containing either result [HeroesEntity] or [Throwable] if something went wrong
	 */
	fun getHeroes(): IO<Either<Throwable, HeroesEntity>>

	/**
	 * Get [HeroEntity]
	 *
	 * @return [Either] containing either result [HeroEntity] or [Throwable] if something went wrong
	 */
	suspend fun getHero(heroId: HeroId): Either<Throwable, HeroEntity>

	/**
	 * Get [HeroesEntity] that are saved as favorites
	 *
	 * @return [Either] containing either result [HeroEntity] or [Throwable] if something went wrong
	 */
	suspend fun getFavorites(): Either<Throwable, HeroesEntity>

	/**
	 * Save [HeroEntity]
	 *
	 * @return [Either] containing either [Unit] or [Throwable] if something went wrong
	 */
	suspend fun saveHero(hero: HeroEntity): Either<Throwable, Unit>

	/**
	 * Save [HeroesEntity]
	 *
	 * @return [Either] containing either [Unit] or [Throwable] if something went wrong
	 */
	suspend fun saveHeroes(heroes: HeroesEntity): Either<Throwable, Unit>
}