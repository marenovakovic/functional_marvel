package com.marko.domain.usecases

import arrow.core.Either
import arrow.effects.IO
import com.marko.domain.entities.HeroEntity
import com.marko.domain.entities.HeroId
import com.marko.domain.heroes.HeroesRepository
import javax.inject.Inject

/**
 * Executes business logic for fetching [HeroEntity]
 *
 * @param heroesRepository [HeroesRepository]
 */
class FetchHero @Inject constructor(
	private val heroesRepository: HeroesRepository
) : UseCase<HeroId, HeroEntity>() {

	override fun execute(parameters: HeroId): IO<Either<Throwable, HeroEntity>> =
		heroesRepository.getHero(heroId = parameters)
}