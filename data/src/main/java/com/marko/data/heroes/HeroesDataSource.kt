package com.marko.data.heroes

import arrow.core.Either
import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.domain.entities.HeroId

interface HeroesDataSource {

	suspend fun getHeroes(): Either<Throwable, HeroesData>

	suspend fun getHero(heroId: HeroId): Either<Throwable, HeroData>

	suspend fun saveHero(hero: HeroData): Either<Throwable, Unit>

	suspend fun saveHeroes(heroes: HeroesData): Either<Throwable, Unit>

	suspend fun getFavorites(): Either<Throwable, HeroesData>

	suspend fun setFavorite(heroId: HeroId): Either<Throwable, Unit>

	suspend fun removeFavorite(heroId: HeroId): Either<Throwable, Unit>
}