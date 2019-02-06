package com.marko.data.heroes

import arrow.core.Either
import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.domain.entities.HeroId

interface HeroesRemoteRepository {

	suspend fun fetchHeroes(): Either<Throwable, HeroesData>

	suspend fun fetchHero(heroId: HeroId): Either<Throwable, HeroData>
}