package com.marko.domain.usecases

import arrow.core.Left
import arrow.core.Right
import arrow.effects.IO
import com.marko.domain.arrow.shouldBeLeft
import com.marko.domain.arrow.shouldBeRight
import com.marko.domain.entities.HeroEntity
import com.marko.domain.heroes.HeroesRepository
import com.marko.domain.sampledata.hero
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class FetchHeroTest {

	private val heroesRepository = mockk<HeroesRepository>()
	private val fetchHero = FetchHero(heroesRepository = heroesRepository)

	@Test
	fun `check does use case calls repository`() {
		val heroId = "1"
		val heroEntity = hero(id = heroId)

		heroesRepository.stubHero(heroEntity)

		fetchHero(parameters = heroId).unsafeRunSync()

		verify(exactly = 1) { heroesRepository.getHero(heroId = heroId) }
	}

	@Test
	fun `check is use case result Right`() {
		val heroEntity = hero()

		heroesRepository.stubHero(heroEntity)

		val heroId = "1"
		val result = fetchHero(parameters = heroId).unsafeRunSync()

		result shouldBeRight heroEntity
	}

	@Test
	fun `check is use case result Left when something goes wrong`() {
		val t = Throwable("jeb' se")

		heroesRepository.stubThrow(t)

		val heroId = "1"
		val result = fetchHero(parameters = heroId).unsafeRunSync()

		result shouldBeLeft t
	}

	private fun HeroesRepository.stubHero(heroEntity: HeroEntity) {
		every { getHero(any()) } returns IO.just(Right(heroEntity))
	}

	private fun HeroesRepository.stubThrow(t: Throwable) {
		every { getHero(any()) } returns IO.just(Left(t))
	}
}