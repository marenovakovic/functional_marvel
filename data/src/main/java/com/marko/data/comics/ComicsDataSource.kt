package com.marko.data.comics

import arrow.core.Either
import com.marko.data.entities.ComicsData
import com.marko.domain.entities.HeroId

interface ComicsDataSource {

	suspend fun getComics(): Either<Throwable, ComicsData>

	suspend fun getComicsForHero(heroId: HeroId): Either<Throwable, ComicsData>
}