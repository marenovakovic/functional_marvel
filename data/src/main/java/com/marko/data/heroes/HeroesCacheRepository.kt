package com.marko.data.heroes

import arrow.core.Either
import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.domain.entities.HeroId

interface HeroesCacheRepository {

	suspend fun saveHero(hero: HeroData): Either<Throwable, Unit>

	suspend fun saveHeroes(heroes: HeroesData): Either<Throwable, Unit>

	suspend fun queryHeroes(): Either<Throwable, HeroesData>

	suspend fun queryHero(heroId: HeroId): Either<Throwable, HeroData>
}