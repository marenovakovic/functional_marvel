package com.marko.data.heroes

import arrow.core.Either
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
	@Named(DI.HEROES_REMOTE_SOURCE) private val heroesRemoteSource: HeroesDataSource,
	@Named(DI.HEROES_CACHE_SOURCE) private val heroesCacheSource: HeroesDataSource
) : HeroesRepository {

	override fun getHeroes(): IO<Either<Throwable, HeroesEntity>> = fx {
		val cachedHeroes = ! effect { heroesCacheSource.getHeroes() }
		if (cachedHeroes.isRight() && cachedHeroes.exists { it.isNotEmpty() }) return@fx cachedHeroes
		val fetchedHeroes = ! effect { heroesRemoteSource.getHeroes() }
		fetchedHeroes.getOrElse { emptyList() }
			.let { ! effect { saveHeroes(it.toEntity()) } }
		fetchedHeroes
	}
		.map { result -> result.map { heroes -> heroes.toEntity() } }

	override fun getHero(heroId: HeroId): IO<Either<Throwable, HeroEntity>> = fx {
		val cachedHero = ! effect { heroesCacheSource.getHero(heroId = heroId) }
		if (cachedHero.isRight() && cachedHero.exists { hero -> hero.id.isNotBlank() }) return@fx cachedHero
		! effect { heroesRemoteSource.getHero(heroId = heroId) }
	}
		.map { result -> result.map { hero -> hero.toEntity() } }

	override suspend fun saveHero(hero: HeroEntity): Either<Throwable, Unit> =
		heroesCacheSource.saveHero(hero.toData())

	override suspend fun saveHeroes(heroes: HeroesEntity): Either<Throwable, Unit> =
		heroesCacheSource.saveHeroes(heroes.toData())

	override suspend fun getFavorites(): Either<Throwable, HeroesEntity> =
		heroesCacheSource.getFavorites().map { it.toEntity() }

	override fun setFavorite(heroId: HeroId): IO<Either<Throwable, Unit>> = fx {
		! effect { heroesCacheSource.setFavorite(heroId = heroId) }
	}

	override fun removeFavorite(heroId: HeroId): IO<Either<Throwable, Unit>> = fx {
		! effect { heroesCacheSource.removeFavorite(heroId = heroId) }
	}
}