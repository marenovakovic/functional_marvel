package com.marko.domain.usecases

import arrow.core.Either
import arrow.effects.IO
import com.marko.domain.comics.ComicsRepository
import com.marko.domain.entities.ComicsEntity
import com.marko.domain.entities.HeroId
import javax.inject.Inject

/**
 * Executes business logic for fetching [ComicsEntity] related to specific hero
 *
 * @param comicsRepository [ComicsRepository]
 */
class FetchComicsForHero @Inject constructor(
	private val comicsRepository: ComicsRepository
) : UseCase<HeroId, ComicsEntity>() {

	override fun execute(parameters: HeroId): IO<Either<Throwable, ComicsEntity>> =
		comicsRepository.getComicsForHero(heroId = parameters)
}