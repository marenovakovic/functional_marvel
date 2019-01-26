package com.marko.functional_marvel.domain.comics

import arrow.Kind
import com.marko.functional_marvel.entities.Comics

/**
 * Data layer access point for [Comics]
 *
 * [F] Higher-kind type
 */
interface ComicsRepository<F> {

	/**
	 * Get [Comics]
	 *
	 * @return [Kind]
	 */
	fun getComics(): Kind<F, Comics>
}