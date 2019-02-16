package com.marko.domain.usecases

import arrow.core.Left
import arrow.core.Right
import arrow.effects.IO
import com.marko.domain.arrow.shouldBeLeft
import com.marko.domain.arrow.shouldBeRight
import com.marko.domain.comics.ComicsRepository
import com.marko.domain.entities.ComicsEntity
import com.marko.domain.sampledata.sampleComics
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class FetchComicsForHeroTest {

	private val comicsRepository = mockk<ComicsRepository>()
	private val fetchComicsForHero = FetchComicsForHero(comicsRepository = comicsRepository)

	@Test
	fun `does use case calls repository`() {
		val heroId = "1"
		val comics = sampleComics

		comicsRepository.stubComics(comics)

		fetchComicsForHero(parameters = heroId)

		verify(exactly = 1) { comicsRepository.getComicsForHero(heroId = heroId) }
	}

	@Test
	fun `is result Right`() {
		val comics = sampleComics

		comicsRepository.stubComics(comics)

		val result = fetchComicsForHero(parameters = "1").unsafeRunSync()

		result shouldBeRight comics
	}

	@Test
	fun `is result Left when something goes wrong`() {
		val t = Throwable("nem'm")

		comicsRepository.stubThrow(t)

		val result = fetchComicsForHero(parameters = "1").unsafeRunSync()

		result shouldBeLeft t
	}

	private fun ComicsRepository.stubComics(comics: ComicsEntity) {
		every { getComicsForHero(any()) } returns IO.just(Right(comics))
	}

	private fun ComicsRepository.stubThrow(t: Throwable) {
		every { getComicsForHero(any()) } returns IO.just(Left(t))
	}
}