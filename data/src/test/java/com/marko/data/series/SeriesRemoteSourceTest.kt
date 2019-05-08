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

class SeriesRemoteSourceTest {

	private val remoteRepository = mockk<SeriesRemoteRepository>()
	private val remoteSource = SeriesRemoteSource(seriesRemoteRepository = remoteRepository)

	@Test
	fun `does getSeriesForHero calls repository`() = runBlocking {
		val heroId = "1"
		val series = seriesDatas

		remoteRepository.stubSeries(series)

		remoteSource.getSeriesForHero(heroId = heroId)

		coVerify(exactly = 1) { remoteRepository.fetchSeriesForHero(heroId = heroId) }
	}

	@Test
	fun `is getSeriesForHero Right`() = runBlocking {
		val series = seriesDatas

		remoteRepository.stubSeries(series)

		val result = remoteSource.getSeriesForHero(heroId = "1")

		result shouldBeRight series
	}

	@Test
	fun `is getSeriesForHero result Left when something goes wrong`() = runBlocking {
		val t = Throwable("nem'm")

		remoteRepository.stubThrow(t)

		val result = remoteSource.getSeriesForHero(heroId = "1")

		result shouldBeLeft t
	}

	private fun SeriesRemoteRepository.stubSeries(series: List<SeriesData>) {
		coEvery { fetchSeriesForHero(any()) } returns Right(series)
	}

	private fun SeriesRemoteRepository.stubThrow(t: Throwable) {
		coEvery { fetchSeriesForHero(any()) } returns Left(t)
	}
}