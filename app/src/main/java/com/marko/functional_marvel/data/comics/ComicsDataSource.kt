package com.marko.functional_marvel.data.comics

import arrow.Kind
import com.marko.functional_marvel.entities.Comics

/**
 * Data source describing common [Comics] related operations
 *
 * [F] Higher-kind type
 */
interface ComicsDataSource<F> {

	/**
	 * Get [Comics]
	 *
	 * @return [Kind]
	 */
	fun getComics(): Kind<F, Comics>
}