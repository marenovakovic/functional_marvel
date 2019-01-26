package com.marko.functional_marvel.usecases

import arrow.Kind
import com.marko.functional_marvel.domain.comics.ComicsRepository
import com.marko.functional_marvel.entities.Comics
import javax.inject.Inject

/**
 * Executes business logic for fetching [Comics]
 *
 * [F] Higher-kind type
 *
 * @param comicsRepository [ComicsRepository]
 */
class FetchComics<F> @Inject constructor(
	private val comicsRepository: ComicsRepository<F>
) : UseCase<F, Unit, Comics> {

	override fun execute(parameters: Unit): Kind<F, Comics> = comicsRepository.getComics()
}