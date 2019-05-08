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
	 * @param heroId [HeroId]
	 *
	 * @return [IO] with [Either] containing either result [HeroEntity] or [Throwable] if something went wrong
	 */
	fun getHero(heroId: HeroId): IO<Either<Throwable, HeroEntity>>

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
	 * Get [HeroesEntity] that are saved as favorites
	 *
	 * @return [Either] containing either result [HeroEntity] or [Throwable] if something went wrong
	 */
	suspend fun getFavorites(): Either<Throwable, HeroesEntity>

	/**
	 * Set [HeroEntity] as favorite
	 *
	 * @return [IO] with [Either] containing either [Unit] or [Throwable] if something went wrong
	 */
	fun setFavorite(heroId: HeroId): IO<Either<Throwable, Unit>>

	/**
	 * Remove [HeroEntity] from favorites
	 *
	 * @return [IO] with [Either] containing either [Unit] or [Throwable] if something went wrong
	 */
	fun removeFavorite(heroId: HeroId): IO<Either<Throwable, Unit>>
}