package com.marko.domain.usecases

import arrow.core.Left
import arrow.core.Right
import com.marko.domain.entities.HeroesEntity
import com.marko.domain.heroes.HeroesRepository
import com.marko.domain.sampledata.sampleHeroes
import io.kotlintest.assertions.arrow.either.shouldBeLeft
import io.kotlintest.assertions.arrow.either.shouldBeRight
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class FetchHeroesTest {

	private val heroesRepository = mockk<HeroesRepository>()
	private val fetchHeroes = FetchHeroes(heroesRepository = heroesRepository)

	@Test
	fun `check does use case completes without exceptions and does it calls repository`() = runBlocking {
		val stubHeroes = sampleHeroes
		heroesRepository.stubHeroes(stubHeroes)

		val result = fetchHeroes()

		result.shouldBeRight(stubHeroes)
		coVerify(exactly = 1) { heroesRepository.getHeroes() }
	}

	@Test
	fun `check result when exception occurs`() = runBlocking {
		val t = Throwable("dalje neces moci")
		heroesRepository.stubThrow(t)

		val result = fetchHeroes()

		result.shouldBeLeft(t)
		coVerify(exactly = 1) { heroesRepository.getHeroes() }
	}

	private fun HeroesRepository.stubHeroes(heroes: HeroesEntity) {
		coEvery { getHeroes() } returns Right(heroes)
	}

	private fun HeroesRepository.stubThrow(t: Throwable) {
		coEvery { getHeroes() } returns Left(t)
	}
}