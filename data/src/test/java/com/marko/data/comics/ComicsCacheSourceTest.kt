package com.marko.data.comics

import arrow.core.Left
import arrow.core.Right
import com.marko.data.arrow.shouldBeLeft
import com.marko.data.arrow.shouldBeRight
import com.marko.data.entities.ComicsData
import com.marko.data.sampledata.comicsData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class ComicsCacheSourceTest {

	private val cacheRepository = mockk<ComicsCacheRepository>()
	private val cacheSource = ComicsCacheSource(comicsCacheRepository = cacheRepository)

	@Test
	fun `does queryComics calls repository`() = runBlocking {
		val comics = comicsData

		cacheRepository.stubComics(comics)

		cacheSource.getComics()

		coVerify(exactly = 1) { cacheRepository.queryComics() }
	}

	@Test
	fun `check is queryComics result Right`() = runBlocking {
		val comics = comicsData

		cacheRepository.stubComics(comics)

		val result = cacheSource.getComics()

		result shouldBeRight comics
	}

	@Test
	fun `check is queryComics result Left when something goes wrong`() = runBlocking {
		val t = Throwable("nem'm")

		cacheRepository.stubThrow(t)

		val result = cacheSource.getComics()

		result shouldBeLeft t
	}

	private fun ComicsCacheRepository.stubComics(comics: ComicsData) {
		coEvery { queryComics() } returns Right(comics)
	}

	private fun ComicsCacheRepository.stubThrow(t: Throwable) {
		coEvery { queryComics() } returns Left(t)
	}
}