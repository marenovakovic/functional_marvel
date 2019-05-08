package com.marko.domain.usecases

import arrow.core.Either
import arrow.effects.IO
import com.marko.domain.entities.HeroesEntity
import com.marko.domain.heroes.HeroesRepository
import javax.inject.Inject

/**
 * Executes business logic for fetching [HeroesEntity]
 *
 * @param heroesRepository [HeroesRepository]
 */
class FetchHeroes @Inject constructor(
	private val heroesRepository: HeroesRepository
) : UseCase<Unit, HeroesEntity>() {

	override fun execute(parameters: Unit): IO<Either<Throwable, HeroesEntity>> =
		heroesRepository.getHeroes()
}