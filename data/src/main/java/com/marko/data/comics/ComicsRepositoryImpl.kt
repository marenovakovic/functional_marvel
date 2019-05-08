package com.marko.data.comics

import arrow.core.Either
import arrow.core.getOrElse
import arrow.effects.IO
import arrow.effects.extensions.io.fx.fx
import com.marko.data.mappers.toEntity
import com.marko.domain.comics.ComicsRepository
import com.marko.domain.entities.ComicsEntity
import com.marko.domain.entities.HeroId
import javax.inject.Inject

/**
 * [ComicsRepository] implementations
 *
 * @param comicsRemoteSource [ComicsDataSource] API access point
 *
 * @param comicsCacheSource [ComicsDataSource] database access point
 */
class ComicsRepositoryImpl @Inject constructor(
	private val comicsRemoteSource: ComicsDataSource,
	private val comicsCacheSource: ComicsDataSource
) : ComicsRepository {

	override fun getComics(): IO<Either<Throwable, ComicsEntity>> = fx {
		val cachedComics = ! effect { comicsCacheSource.getComics() }

		if (cachedComics.isLeft() || cachedComics.getOrElse { emptyList() }.isEmpty()) ! effect { comicsRemoteSource.getComics() }
		else cachedComics
	}
		.map { result -> result.map { comics -> comics.toEntity() } }

	override fun getComicsForHero(heroId: HeroId): IO<Either<Throwable, ComicsEntity>> = fx {
		val cachedComics = ! effect { comicsCacheSource.getComics() }

		if (cachedComics.isLeft() || cachedComics.getOrElse { emptyList() }.isEmpty()) ! effect { comicsRemoteSource.getComics() }
		else cachedComics
	}
		.map { result -> result.map { comics -> comics.toEntity() } }
}