package com.marko.cache.heroes

import arrow.core.Either
import arrow.core.Try
import com.marko.cache.mappers.toCache
import com.marko.cache.mappers.toData
import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.data.heroes.HeroesCacheRepository
import com.marko.domain.entities.HeroId
import javax.inject.Inject

/**
 * [HeroesCacheRepository] implementation. Database access point
 *
 * @param heroesDao [HeroesDao] for accessing database
 */
class HeroesCacheRepositoryImpl @Inject constructor(
	private val heroesDao: HeroesDao
) : HeroesCacheRepository {

	override suspend fun saveHero(hero: HeroData): Either<Throwable, Unit> =
		Try { heroesDao.saveHero(hero = hero.toCache()) }.toEither()

	override suspend fun saveHeroes(heroes: HeroesData): Either<Throwable, Unit> =
		Try { heroesDao.saveHeroes(heroes = heroes.toCache()) }.toEither()

	override suspend fun queryHeroes(): Either<Throwable, HeroesData> =
		Try { heroesDao.queryAllHeroes() }.map { it.toData() }.toEither()

	override suspend fun queryHero(heroId: HeroId): Either<Throwable, HeroData> =
		Try { heroesDao.queryHero(heroId = heroId) }.map { it.toData() }.toEither()
}