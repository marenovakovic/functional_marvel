package com.marko.domain.usecases

import arrow.core.Either
import arrow.effects.IO
import com.marko.domain.entities.HeroEntity
import javax.inject.Inject

/**
 * Executes business logic to set or remove favorite depending on is hero currently favorite or not
 *
 * @param setFavorite [SetFavorite]
 *
 * @param removeFavorite [RemoveFavorite]
 */
class ResolveFavorite @Inject constructor(
	private val setFavorite: SetFavorite,
	private val removeFavorite: RemoveFavorite
) : UseCase<HeroEntity, Unit>() {

	override fun execute(parameters: HeroEntity): IO<Either<Throwable, Unit>> =
		if (parameters.isFavorite) removeFavorite(parameters = parameters.id)
		else setFavorite(parameters = parameters.id)
}