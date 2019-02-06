package com.marko.data.heroes

import arrow.core.Either
import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.domain.entities.HeroId
import javax.inject.Inject

class HeroesCacheSource @Inject constructor(
	private val heroesCacheRepository: HeroesCacheRepository
) : HeroesDataSource {

	override suspend fun saveHero(heroData: HeroData): Either<Throwable, Unit> =
		heroesCacheRepository.saveHero(heroData)

	override suspend fun saveHeroes(heroesData: HeroesData): Either<Throwable, Unit> =
		heroesCacheRepository.saveHeroes(heroes = heroesData)

	override suspend fun getHeroes(): Either<Throwable, HeroesData> =
		heroesCacheRepository.queryHeroes()

	override suspend fun getHero(heroId: HeroId): Either<Throwable, HeroData> =
		heroesCacheRepository.queryHero(heroId = heroId)
}