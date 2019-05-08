package com.marko.domain.usecases

import arrow.core.Either
import arrow.effects.IO
import com.marko.domain.entities.HeroId
import com.marko.domain.heroes.HeroesRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

/**
 * Executes business logic for saving [HeroEntity] to favorites
 *
 * @param heroesRepository [HeroesRepository]
 */
class SetFavorite @Inject constructor(
	private val heroesRepository: HeroesRepository
) : UseCase<HeroId, Unit>() {

	override fun execute(parameters: HeroId): IO<Either<Throwable, Unit>> =
		heroesRepository.setFavorite(heroId = parameters)
}