package com.marko.data.heroes

import arrow.core.Either
import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.domain.entities.HeroId

interface HeroesDataSource {

	suspend fun saveHero(heroData: HeroData): Either<Throwable, Unit>

	suspend fun saveHeroes(heroesData: HeroesData): Either<Throwable, Unit>

	suspend fun getHeroes(): Either<Throwable, HeroesData>

	suspend fun getHero(heroId: HeroId): Either<Throwable, HeroData>
}