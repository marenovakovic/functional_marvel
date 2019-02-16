package com.marko.data.comics

import arrow.core.Either
import com.marko.data.entities.ComicsData
import com.marko.domain.entities.HeroId
import javax.inject.Inject

class ComicsCacheSource @Inject constructor(
	private val comicsCacheRepository: ComicsCacheRepository
) : ComicsDataSource {

	override suspend fun getComics(): Either<Throwable, ComicsData> =
		comicsCacheRepository.queryComics()

	override suspend fun getComicsForHero(heroId: HeroId): Either<Throwable, ComicsData> =
		comicsCacheRepository.queryComicsForHero(heroId = heroId)
}