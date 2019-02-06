package com.marko.domain.heroes

import arrow.core.Either
import com.marko.domain.entities.HeroEntity
import com.marko.domain.entities.HeroId
import com.marko.domain.entities.HeroesEntity

/**
 * Data layer access point for [HeroesEntity]
 */
interface HeroesRepository {

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

	/**
	 * Get [HeroesEntity]
	 *
	 * @return [Either] containing either result [HeroesEntity] or [Throwable] if something went wrong
	 */
	suspend fun getHeroes(): Either<Throwable, HeroesEntity>

	/**
	 * Get [HeroEntity]
	 *
	 * @return [Either] containing either result [HeroEntity] or [Throwable] if something went wrong
	 */
	suspend fun getHero(heroId: HeroId): Either<Throwable, HeroEntity>
}