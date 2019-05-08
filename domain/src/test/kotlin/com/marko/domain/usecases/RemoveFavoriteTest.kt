package com.marko.domain.usecases

import arrow.core.Left
import arrow.core.Right
import arrow.effects.IO
import com.marko.domain.arrow.shouldBeLeft
import com.marko.domain.arrow.shouldBeRight
import com.marko.domain.heroes.HeroesRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class RemoveFavoriteTest {

	private val heroesRepository = mockk<HeroesRepository>()
	private val removeFavorite = RemoveFavorite(heroesRepository = heroesRepository)

	@Test
	fun `is repository called`() {
		val heroId = "1"

		heroesRepository.stubRemoveFavorite()

		removeFavorite(parameters = heroId).unsafeRunSync()

		verify(exactly = 1) { heroesRepository.removeFavorite(heroId = heroId) }
	}

	@Test
	fun `is result Right`() {
		heroesRepository.stubRemoveFavorite()

		val result = removeFavorite(parameters = "1").unsafeRunSync()

		result shouldBeRight Unit
	}

	@Test
	fun `is result Left when something goes wrong`() {
		val t = Throwable("nemeze")

		heroesRepository.stubThrow(t)

		val result = removeFavorite(parameters = "1").unsafeRunSync()

		result shouldBeLeft t
	}

	private fun HeroesRepository.stubRemoveFavorite() {
		every { removeFavorite(any()) } returns IO.just(Right(Unit))
	}

	private fun HeroesRepository.stubThrow(t: Throwable) {
		every { removeFavorite(any()) } returns IO.just(Left(t))
	}
}