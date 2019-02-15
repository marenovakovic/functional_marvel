package com.marko.data.comics

import arrow.core.Either
import arrow.core.getOrElse
import arrow.effects.IO
import arrow.effects.extensions.io.fx.fx
import com.marko.data.injection.DI
import com.marko.data.mappers.toEntity
import com.marko.domain.comics.ComicsRepository
import com.marko.domain.entities.ComicsEntity
import javax.inject.Inject
import javax.inject.Named

/**
 * [ComicsRepository] implementations
 *
 * @param comicsRemoteSource [ComicsDataSource] API access point
 *
 * @param comicsCacheSource [ComicsDataSource] database access point
 */
class ComicsRepositoryImpl @Inject constructor(
	@Named(DI.COMICS_REMOTE_SOURCE) private val comicsRemoteSource: ComicsDataSource,
	@Named(DI.COMICS_REMOTE_SOURCE) private val comicsCacheSource: ComicsDataSource
) : ComicsRepository {

	override fun getComics(): IO<Either<Throwable, ComicsEntity>> = fx {
		val cachedComics = ! effect { comicsCacheSource.getComics() }

		if (cachedComics.isLeft() || cachedComics.getOrElse { emptyList() }.isEmpty()) ! effect { comicsRemoteSource.getComics() }
		else cachedComics
	}
		.map { result -> result.map { comics -> comics.toEntity() } }
}