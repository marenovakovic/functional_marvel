package com.marko.data.series

import arrow.core.Either
import com.marko.data.entities.SeriesData
import com.marko.domain.entities.HeroId

interface SeriesRemoteRepository {

	suspend fun fetchSeriesForHero(heroId: HeroId): Either<Throwable, List<SeriesData>>
}