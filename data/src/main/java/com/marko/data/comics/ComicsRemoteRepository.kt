package com.marko.data.comics

import arrow.core.Either
import com.marko.data.entities.ComicsData
import com.marko.domain.entities.HeroId

interface ComicsRemoteRepository {

	suspend fun fetchComics(): Either<Throwable, ComicsData>

	suspend fun fetchComicsForHero(heroId: HeroId): Either<Throwable, ComicsData>
}