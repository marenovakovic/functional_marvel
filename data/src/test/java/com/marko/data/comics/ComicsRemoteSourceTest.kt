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

internal class ComicsRemoteSourceTest {

	private val remoteRepository = mockk<ComicsRemoteRepository>()
	private val remoteSource = ComicsRemoteSource(comicsRemoteRepository = remoteRepository)

	@Test
	fun `does fetchComics calls repository`() = runBlocking {
		val comics = comicsData

		remoteRepository.stubComics(comics)

		remoteSource.getComics()

		coVerify(exactly = 1) { remoteRepository.fetchComics() }
	}

	@Test
	fun `check fetchComics is result Right`() = runBlocking {
		val comics = comicsData

		remoteRepository.stubComics(comics)

		val result = remoteSource.getComics()

		result shouldBeRight comics
	}

	@Test
	fun `check is fetchComics result Left when something goes wrong`() = runBlocking {
		val t = Throwable("nem'm")

		remoteRepository.stubThrow(t)

		val result = remoteSource.getComics()

		result shouldBeLeft t
	}

	@Test
	fun `does getComicsForHero call repository`() = runBlocking {
		val heroId = "1"
		val comics = comicsData

		remoteRepository.stubComics(comics)

		remoteSource.getComicsForHero(heroId = heroId)

		coVerify(exactly = 1) { remoteRepository.fetchComicsForHero(heroId = heroId) }
	}

	@Test
	fun `is queryComicsForHero result Right`() = runBlocking {
		val heroId = "1"
		val comics = comicsData

		remoteRepository.stubComics(comics)

		val result = remoteSource.getComicsForHero(heroId = heroId)

		result shouldBeRight comics
	}

	@Test
	fun `is queryComicsForHero result Left when something goes wrong`() = runBlocking {
		val t = Throwable("nem'm")

		remoteRepository.stubThrow(t)

		val result = remoteSource.getComicsForHero("1")

		result shouldBeLeft t
	}

	private fun ComicsRemoteRepository.stubComics(comics: ComicsData) {
		coEvery { fetchComics() } returns Right(comics)
		coEvery { fetchComicsForHero(any()) } returns Right(comics)
	}

	private fun ComicsRemoteRepository.stubThrow(t: Throwable) {
		coEvery { fetchComics() } returns Left(t)
		coEvery { fetchComicsForHero(any()) } returns Left(t)
	}
}