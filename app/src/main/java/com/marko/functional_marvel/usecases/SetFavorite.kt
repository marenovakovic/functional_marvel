package com.marko.functional_marvel.usecases

import arrow.Kind
import com.marko.functional_marvel.domain.heroes.HeroesRepository
import com.marko.functional_marvel.entities.Hero
import javax.inject.Inject

/**
 * Executes business logic for saving [Hero]
 *
 * [F] Higher-kind type
 *
 * @param heroesRepository [HeroesRepository]
 */
class SetFavorite<F> @Inject constructor(
	private val heroesRepository: HeroesRepository<F>
) : UseCase<F, Hero, Unit> {

	override fun execute(parameters: Hero): Kind<F, Unit> =
		heroesRepository.saveHero(hero = parameters)
}