package com.marko.domain.usecases

import arrow.core.Either
import arrow.effects.IO
import com.marko.domain.comics.ComicsRepository
import com.marko.domain.entities.ComicsEntity
import javax.inject.Inject

/**
 * Executes business logic for fetching [ComicsEntity]
 *
 * @param comicsRepository [ComicsRepository]
 */
class FetchComics @Inject constructor(
	private val comicsRepository: ComicsRepository
) : UseCase<Unit, ComicsEntity>() {

	override fun execute(parameters: Unit): IO<Either<Throwable, ComicsEntity>> =
		comicsRepository.getComics()
}