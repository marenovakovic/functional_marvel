package com.marko.functional_marvel.data.comics

import arrow.Kind
import arrow.effects.typeclasses.MonadDefer
import com.marko.functional_marvel.domain.comics.ComicsRepository
import com.marko.functional_marvel.entities.Comics
import com.marko.functional_marvel.exceptions.ContentNotAvailable
import javax.inject.Inject

/**
 * [ComicsRepository] implementation. Implements [MonadDefer] by delegating
 *
 * [F] Higher-kind type
 *
 * @param monadDefer [MonadDefer] for lazy evaluation
 *
 * @param comicsDataSource [ComicsDataSource]
 */
class ComicsRepositoryImpl<F> @Inject constructor(
	private val monadDefer: MonadDefer<F>,
	private val comicsDataSource: ComicsDataSource<F>
) : ComicsRepository<F>, MonadDefer<F> by monadDefer {

	override fun getComics(): Kind<F, Comics> =
		defer { comicsDataSource.getComics() }
			.handleErrorWith { raiseError(ContentNotAvailable) }

	override fun <A, B> Kind<F, A>.ap(ff: Kind<F, (A) -> B>): Kind<F, B> =
		monadDefer.run { this@ap.ap(ff) }

	override fun <A, B> Kind<F, A>.map(f: (A) -> B): Kind<F, B> =
		monadDefer.run { this@map.map(f) }
}