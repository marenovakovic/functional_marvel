package com.marko.functional_marvel.usecases

import arrow.Kind
import com.marko.functional_marvel.domain.HeroesRepository
import com.marko.functional_marvel.entities.Heroes
import javax.inject.Inject

/**
 * Executes business logic for fetching [Heroes]
 *
 * @param heroesRepository [HeroesRepository]
 */
class FetchHeroes<F> @Inject constructor(
	private val heroesRepository: HeroesRepository<F>
) : UseCase<F, Unit, Heroes> {

	override fun execute(parameters: Unit): Kind<F, Heroes> = heroesRepository.getHeroes()
}