package com.marko.data.heroes

import arrow.core.Either
import arrow.core.Right
import arrow.core.flatMap
import arrow.core.getOrElse
import arrow.effects.IO
import arrow.effects.extensions.io.fx.fx
import com.marko.data.injection.DI
import com.marko.data.mappers.toData
import com.marko.data.mappers.toEntity
import com.marko.domain.entities.HeroEntity
import com.marko.domain.entities.HeroId
import com.marko.domain.entities.HeroesEntity
import com.marko.domain.heroes.HeroesRepository
import javax.inject.Inject
import javax.inject.Named

/**
 * [HeroesRepository] implementations
 *
 * @param heroesRemoteSource [HeroesDataSource] API access point
 *
 * @param heroesCacheSource [HeroesDataSource] database access point
 */
class HeroesRepositoryImpl @Inject constructor(
	@Named(DI.REMOTE_SOURCE) private val heroesRemoteSource: HeroesDataSource,
	@Named(DI.CACHE_SOURCE) private val heroesCacheSource: HeroesDataSource
) : HeroesRepository {

	override fun getHeroes(): IO<Either<Throwable, HeroesEntity>> = fx {
		val cachedHeroes = ! effect { heroesCacheSource.getHeroes() }

		if (cachedHeroes.isLeft() || cachedHeroes.getOrElse { emptyList() }.isEmpty()) ! effect { heroesRemoteSource.getHeroes() }
		else cachedHeroes
	}
		.map { result -> result.map { heroes -> heroes.toEntity() } }

//	private suspend fun test() {
//		heroesCacheSource.getHeroes()
//			.let { cacheResult ->
//				if (cacheResult.isLeft()) heroesRemoteSource.getHeroes()
//				else cacheResult
//			}
//			.flatMap { cachedHeroes ->
//				if (cachedHeroes.isEmpty()) {
//					val fetchedHeroes = heroesRemoteSource.getHeroes()
//					fetchedHeroes.fold({}, { heroesCacheSource.saveHeroes(it) })
//					fetchedHeroes
//				} else {
//					val heroes = if (cachedHeroes.size < 50) {
//						val missingHeroes = heroesRemoteSource.getHeroes() // od - do
//						missingHeroes.fold({}, { heroesCacheSource.saveHeroes(it) })
//						cachedHeroes + missingHeroes.toOption().getOrElse { listOf() }
//					} else {
//						cachedHeroes
//					}
//					Right(heroes)
//				}
//			}
//			.map { it.toEntity() }
//	}

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

	override suspend fun getFavorites(): Either<Throwable, HeroesEntity> =
		heroesCacheSource.getFavorites().map { it.toEntity() }

	override suspend fun saveHero(hero: HeroEntity): Either<Throwable, Unit> =
		heroesCacheSource.saveHero(hero.toData())

	override suspend fun saveHeroes(heroes: HeroesEntity): Either<Throwable, Unit> =
		heroesCacheSource.saveHeroes(heroes.toData())
}