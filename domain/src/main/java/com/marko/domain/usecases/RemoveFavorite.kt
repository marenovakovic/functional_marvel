package com.marko.domain.usecases

import arrow.core.Either
import arrow.effects.IO
import com.marko.domain.entities.HeroId
import com.marko.domain.heroes.HeroesRepository
import javax.inject.Inject

/**
 * Executes business logic for removing [HeroEntity] from favorites
 *
 * @param heroesRepository [HeroesRepository]
 */
class RemoveFavorite @Inject constructor(
	private val heroesRepository: HeroesRepository
) : UseCase<HeroId, Unit>() {

	override fun execute(parameters: HeroId): IO<Either<Throwable, Unit>> =
		heroesRepository.removeFavorite(heroId = parameters)
}