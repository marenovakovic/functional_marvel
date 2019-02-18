package com.marko.data.series

import arrow.core.Left
import arrow.core.Right
import com.marko.data.arrow.shouldBeLeft
import com.marko.data.arrow.shouldBeRight
import com.marko.data.entities.SeriesData
import com.marko.data.sampledata.seriesDatas
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class SeriesCacheSourceTest {

	private val cacheRepository = mockk<SeriesCacheRepository>()
	private val cacheSource = SeriesCacheSource(seriesCacheRepository = cacheRepository)

	@Test
	fun `does getSeriesForHero calls repository`() = runBlocking {
		val heroId = "1"
		val series = seriesDatas

		cacheRepository.stubSeries(series)

		cacheSource.getSeriesForHero(heroId = heroId)

		coVerify(exactly = 1) { cacheRepository.querySeriesForHero(heroId = heroId) }
	}

	@Test
	fun `is getSeriesForHero result Right`() = runBlocking {
		val series = seriesDatas

		cacheRepository.stubSeries(series)

		val result = cacheSource.getSeriesForHero(heroId = "1")

		result shouldBeRight series
	}

	@Test
	fun `is getSeriesForHero result Left when something goes wrong`() = runBlocking {
		val t = Throwable("nem'm")

		cacheRepository.stubThrow(t)

		val result = cacheSource.getSeriesForHero(heroId = "1")

		result shouldBeLeft t
	}

	private fun SeriesCacheRepository.stubSeries(series: List<SeriesData>) {
		coEvery { querySeriesForHero(any()) } returns Right(series)
	}

	private fun SeriesCacheRepository.stubThrow(t: Throwable) {
		coEvery { querySeriesForHero(any()) } returns Left(t)
	}
}