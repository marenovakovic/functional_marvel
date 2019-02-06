package com.marko.data.heroes

import arrow.core.Either
import arrow.core.Right
import arrow.core.flatMap
import com.marko.data.injection.DI
import com.marko.data.mappers.toData
import com.marko.data.mappers.toEntity
import com.marko.domain.entities.HeroEntity
import com.marko.domain.entities.HeroId
import com.marko.domain.entities.HeroesEntity
import com.marko.domain.heroes.HeroesRepository
import javax.inject.Named

/**
 * [HeroesRepository] implementations
 *
 * @param heroesRemoteSource [HeroesDataSource] API access point
 *
 * @param heroesCacheSource [HeroesDataSource] database access point
 */
class HeroesRepositoryImpl(
	@Named(DI.REMOTE_SOURCE) private val heroesRemoteSource: HeroesDataSource,
	@Named(DI.CACHE_SOURCE) private val heroesCacheSource: HeroesDataSource
) : HeroesRepository {

	override suspend fun saveHero(hero: HeroEntity): Either<Throwable, Unit> =
		heroesCacheSource.saveHero(hero.toData())

	override suspend fun saveHeroes(heroes: HeroesEntity): Either<Throwable, Unit> =
		heroesCacheSource.saveHeroes(heroes.toData())

	override suspend fun getHeroes(): Either<Throwable, HeroesEntity> =
		heroesCacheSource.getHeroes()
			.let { cacheResult ->
				if (cacheResult.isLeft()) heroesRemoteSource.getHeroes()
				else cacheResult
			}
			.flatMap { heroes ->
				if (heroes.isEmpty()) heroesRemoteSource.getHeroes()
				else Right(heroes)
			}
			.map { it.toEntity() }

	override suspend fun getHero(heroId: HeroId): Either<Throwable, HeroEntity> =
		heroesCacheSource.getHero(heroId = heroId)
			.let { cachedResult ->
				if (cachedResult.isLeft()) heroesRemoteSource.getHero(heroId = heroId)
				else cachedResult
			}
			.flatMap {
				if (it.id.isBlank()) heroesRemoteSource.getHero(heroId = heroId)
				else Right(it)
			}
			.map { it.toEntity() }
}