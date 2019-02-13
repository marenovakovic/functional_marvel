package com.marko.data.heroes

import arrow.core.Either
import arrow.core.Left
import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.domain.entities.HeroId
import com.marko.domain.exceptions.UnsupportedCaching
import javax.inject.Inject

class HeroesRemoteSource @Inject constructor(
	private val heroesRemoteRepository: HeroesRemoteRepository
) : HeroesDataSource {

	override suspend fun getHeroes(): Either<Throwable, HeroesData> =
		heroesRemoteRepository.fetchHeroes()

	override suspend fun getHero(heroId: HeroId): Either<Throwable, HeroData> =
		heroesRemoteRepository.fetchHero(heroId = heroId)

	override suspend fun getFavorites(): Either<Throwable, HeroesData> {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override suspend fun saveHero(heroData: HeroData): Either<Throwable, Unit> =
		Left(UnsupportedCaching)

	override suspend fun saveHeroes(heroesData: HeroesData): Either<Throwable, Unit> =
		Left(UnsupportedCaching)
}