package com.marko.data.series

import arrow.core.Either
import com.marko.data.entities.SeriesData
import com.marko.domain.entities.HeroId

interface SeriesDataSource {

	suspend fun getSeriesForHero(heroId: HeroId): Either<Throwable, List<SeriesData>>
}