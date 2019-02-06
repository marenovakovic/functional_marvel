package com.marko.domain.usecases

import arrow.core.Either
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

	override suspend fun execute(parameters: Unit): Either<Throwable, HeroesEntity> =
		heroesRepository.getHeroes()
}