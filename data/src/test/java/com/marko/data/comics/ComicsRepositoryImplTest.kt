package com.marko.data.comics

import arrow.core.Left
import arrow.core.Right
import com.marko.data.arrow.shouldBeRight
import com.marko.data.entities.ComicsData
import com.marko.data.mappers.toEntity
import com.marko.data.sampledata.comicsData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class ComicsRepositoryImplTest {

	private val remoteSource = mockk<ComicsDataSource>()
	private val cacheSource = mockk<ComicsDataSource>()
	private val comicsRepository =
		ComicsRepositoryImpl(comicsRemoteSource = remoteSource, comicsCacheSource = cacheSource)

	@Test
	fun `check that remote source is NOT called when cache source returns valid result for getComics`() {
		val comicsData = comicsData

		cacheSource.stubComics(comicsData)

		val result = comicsRepository.getComics().unsafeRunSync()

		result shouldBeRight comicsData.toEntity()
		coVerify(exactly = 1) { cacheSource.getComics() }
		coVerify(exactly = 0) { remoteSource.getComics() }
	}

	@Test
	fun `check that remote source IS called when cache source returns invalid result for getComics`() {
		val t = Throwable("nem'm")
		val comicsData = comicsData

		remoteSource.stubComics(comicsData)
		cacheSource.stubThrow(t)

		val result = comicsRepository.getComics().unsafeRunSync()

		result shouldBeRight comicsData.toEntity()
		coVerify(exactly = 1) { cacheSource.getComics() }
		coVerify(exactly = 1) { remoteSource.getComics() }
	}

	@Test
	fun `check that remote source is NOT called when cache source returns valid result for getComicsForHero`() {
		val heroId = "1"
		val comicsData = comicsData

		cacheSource.stubComics(comicsData)

		val result = comicsRepository.getComicsForHero(heroId = heroId).unsafeRunSync()

		result shouldBeRight comicsData.toEntity()
		coVerify(exactly = 1) { cacheSource.getComicsForHero(heroId = heroId) }
		coVerify(exactly = 0) { remoteSource.getComicsForHero(any()) }
	}

	@Test
	fun `check that remote source IS called when cache source returns invalid result for getComicsForHero`() {
		val heroId = "1"
		val comicsData = comicsData
		val t = Throwable("nem'm")

		cacheSource.stubThrow(t)
		remoteSource.stubComics(comicsData)

		val result = comicsRepository.getComicsForHero(heroId = heroId).unsafeRunSync()

		result shouldBeRight comicsData.toEntity()
		coVerify(exactly = 1) { cacheSource.getComicsForHero(heroId = heroId) }
		coVerify(exactly = 1) { remoteSource.getComicsForHero(heroId = heroId) }
	}

	private fun ComicsDataSource.stubComics(comics: ComicsData) {
		coEvery { getComics() } returns Right(comics)
		coEvery { getComicsForHero(any()) } returns Right(comics)
	}

	private fun ComicsDataSource.stubThrow(t: Throwable) {
		coEvery { getComics() } returns Left(t)
		coEvery { getComicsForHero(any()) } returns Left(t)
	}
}