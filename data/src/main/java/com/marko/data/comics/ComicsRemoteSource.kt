package com.marko.data.comics

import arrow.core.Either
import com.marko.data.entities.ComicsData
import com.marko.domain.entities.HeroId
import javax.inject.Inject

class ComicsRemoteSource @Inject constructor(
	private val comicsRemoteRepository: ComicsRemoteRepository
) : ComicsDataSource {

	override suspend fun getComics(): Either<Throwable, ComicsData> =
		comicsRemoteRepository.fetchComics()

	override suspend fun getComicsForHero(heroId: HeroId): Either<Throwable, ComicsData> =
		comicsRemoteRepository.fetchComicsForHero(heroId = heroId)
}