package com.marko.domain.comics

import arrow.core.Either
import arrow.effects.IO
import com.marko.domain.entities.ComicsEntity

/**
 * Data layer access point for [ComicsEntity]
 */
interface ComicsRepository {

	/**
	 * Get [ComicsEntity]
	 *
	 * @return [IO] with [Either] containing either result [ComicsEntity] or [Throwable] if something went wrong
	 */
	fun getComics(): IO<Either<Throwable, ComicsEntity>>
}