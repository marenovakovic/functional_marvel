package com.marko.data.comics

import arrow.core.Either
import com.marko.data.entities.ComicsData
import com.marko.domain.entities.HeroId

interface ComicsCacheRepository {

	suspend fun queryComics(): Either<Throwable, ComicsData>

	suspend fun queryComicsForHero(heroId: HeroId): Either<Throwable, ComicsData>
}