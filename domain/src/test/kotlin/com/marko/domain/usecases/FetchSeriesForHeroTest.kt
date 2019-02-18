package com.marko.domain.usecases

import arrow.core.Left
import arrow.core.Right
import arrow.effects.IO
import com.marko.domain.arrow.shouldBeLeft
import com.marko.domain.arrow.shouldBeRight
import com.marko.domain.entities.SeriesEntity
import com.marko.domain.sampledata.multipleSeries
import com.marko.domain.series.SeriesRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class FetchSeriesForHeroTest {

	private val seriesRepository = mockk<SeriesRepository>()
	private val fetchSeriesForHero = FetchSeriesForHero(seriesRepository = seriesRepository)

	@Test
	fun `is repository called`() {
		val heroId = "1"
		val series = multipleSeries

		seriesRepository.stubSeries(series)

		fetchSeriesForHero(parameters = heroId)

		verify { seriesRepository.getSeriesForHero(heroId = heroId) }
	}

	@Test
	fun `is result Right`() {
		val heroId = "1"
		val series = multipleSeries

		seriesRepository.stubSeries(series)

		val result = fetchSeriesForHero(parameters = heroId).unsafeRunSync()

		result shouldBeRight series
	}

	@Test
	fun `is result Left when something goes wrong`() {
		val t = Throwable("nem'm")

		seriesRepository.stubThrow(t)

		val result = seriesRepository.getSeriesForHero("1").unsafeRunSync()

		result shouldBeLeft t
	}

	private fun SeriesRepository.stubSeries(series: List<SeriesEntity>) {
		every { getSeriesForHero(any()) } returns IO.just(Right(series))
	}

	private fun SeriesRepository.stubThrow(t: Throwable) {
		every { getSeriesForHero((any())) } returns IO.just(Left(t))
	}
}