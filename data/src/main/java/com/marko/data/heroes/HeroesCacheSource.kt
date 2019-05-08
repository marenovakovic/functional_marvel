package com.marko.data.heroes

import arrow.core.Either
import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.domain.entities.HeroId
import javax.inject.Inject

class HeroesCacheSource @Inject constructor(
	private val heroesCacheRepository: HeroesCacheRepository
) : HeroesDataSource {

	override suspend fun getHeroes(): Either<Throwable, HeroesData> =
		heroesCacheRepository.queryHeroes()

	override suspend fun getHero(heroId: HeroId): Either<Throwable, HeroData> =
		heroesCacheRepository.queryHero(heroId = heroId)

	override suspend fun saveHero(hero: HeroData): Either<Throwable, Unit> =
		heroesCacheRepository.saveHero(hero)

	override suspend fun saveHeroes(heroes: HeroesData): Either<Throwable, Unit> =
		heroesCacheRepository.saveHeroes(heroes = heroes)

	override suspend fun getFavorites(): Either<Throwable, HeroesData> =
		heroesCacheRepository.queryFavorites()

	override suspend fun setFavorite(heroId: HeroId): Either<Throwable, Unit> =
		heroesCacheRepository.setFavorite(heroId = heroId)

	override suspend fun removeFavorite(heroId: HeroId): Either<Throwable, Unit> =
		heroesCacheRepository.removeFavorite(heroId = heroId)
}