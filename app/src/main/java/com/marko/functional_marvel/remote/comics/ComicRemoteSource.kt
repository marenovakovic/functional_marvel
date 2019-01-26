package com.marko.functional_marvel.remote.comics

import arrow.Kind
import arrow.core.Try
import arrow.effects.typeclasses.Async
import com.karumi.marvelapiclient.ComicApiClient
import com.marko.functional_marvel.data.comics.ComicsDataSource
import com.marko.functional_marvel.entities.Comics
import com.marko.functional_marvel.extensions.safe
import com.marko.functional_marvel.mappers.toComic
import javax.inject.Inject

/**
 * [Comics] API access point. Implements [Async] by delegating
 *
 * @param async [Async] for asynchronous operations
 *
 * @param comicsApi [ComicApiClient] for API access
 *
 * [F] Higher-kind type
 */
class ComicRemoteSource<F> @Inject constructor(
	private val async: Async<F>,
	private val comicsApi: ComicApiClient
) : ComicsDataSource<F>, Async<F> by async {

	override fun getComics(): Kind<F, Comics> = async { callback ->
		val comics = Try { comicsApi.getAll(0, 25) }
			.flatMap { it.safe }
			.map { it.toComic() }
			.toEither()

		callback(comics)
	}

	override fun <A, B> Kind<F, A>.ap(ff: Kind<F, (A) -> B>): Kind<F, B> =
		async.run { this@ap.ap(ff) }

	override fun <A, B> Kind<F, A>.map(f: (A) -> B): Kind<F, B> =
		async.run { this@map.map(f) }
}