package com.marko.data.series

import arrow.core.Left
import arrow.core.Right
import com.marko.data.arrow.shouldBeRight
import com.marko.data.entities.SeriesData
import com.marko.data.mappers.toEntity
import com.marko.data.sampledata.seriesDatas
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.jupiter.api.Test

class SeriesRepositoryImplTest {

	private val remoteSource = mockk<SeriesDataSource>()
	private val cacheSource = mockk<SeriesDataSource>()
	private val seriesRepository =
		SeriesRepositoryImpl(remoteSource = remoteSource, cacheSource = cacheSource)

	@Test
	fun `does getSeriesForHero calls cache source`() {
		val heroId = "1"
		val series = seriesDatas

		cacheSource.stubSeries(series)

		val result = seriesRepository.getSeriesForHero(heroId = heroId).unsafeRunSync()

		result shouldBeRight series.toEntity()
		coVerify(exactly = 1) { cacheSource.getSeriesForHero(heroId = heroId) }
	}

	@Test
	fun `does getSeriesForHero calls remote source when cache source returns Left`() {
		val heroId = "1"
		val series = seriesDatas
		val t = Throwable("nem'm")

		cacheSource.stubThrow(t)
		remoteSource.stubSeries(series)

		val result = seriesRepository.getSeriesForHero(heroId = heroId).unsafeRunSync()

		result shouldBeRight series.toEntity()
		coVerify(exactly = 1) { cacheSource.getSeriesForHero(heroId = heroId) }
		coVerify(exactly = 1) { remoteSource.getSeriesForHero(heroId = heroId) }
	}

	private fun SeriesDataSource.stubSeries(series: List<SeriesData>) {
		coEvery { getSeriesForHero(any()) } returns Right(series)
	}

	private fun SeriesDataSource.stubThrow(t: Throwable) {
		coEvery { getSeriesForHero(any()) } returns Left(t)
	}
}