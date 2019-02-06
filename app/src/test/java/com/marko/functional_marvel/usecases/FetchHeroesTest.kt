package com.marko.functional_marvel.usecases

import arrow.effects.ObservableK
import arrow.effects.value
import com.marko.functional_marvel.domain.heroes.HeroesRepository
import com.marko.functional_marvel.entities.Heroes
import com.marko.functional_marvel.injection.HKImplementation
import com.marko.functional_marvel.sampledata.sampleHeroes
import io.kotlintest.Description
import io.kotlintest.specs.StringSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

internal class FetchHeroesTest : StringSpec() {

	override fun beforeTest(description: Description) {
		clearAllMocks()
	}

	init {
		val heroesRepository = mockk<HeroesRepository<HKImplementation>>()
		val fetchHeroes = FetchHeroes(heroesRepository = heroesRepository)

		"check result and does use case calls repository" {

			val stubHeroes = sampleHeroes
			heroesRepository.stubHeroes(stubHeroes)

			fetchHeroes().value().test()
				.assertComplete()
				.assertValue(stubHeroes)

			verify(exactly = 1) { heroesRepository.getHeroes() }
		}

		"check result when something goes wrong" {
			val t = Throwable("jeb' se")
			heroesRepository.stubThrow(t)

			fetchHeroes().value().test()
				.assertNotComplete()
				.assertError(t)

			verify(exactly = 1) { heroesRepository.getHeroes() }
		}
	}

	private fun HeroesRepository<HKImplementation>.stubHeroes(heroes: Heroes) {
		every { getHeroes() } returns ObservableK.just(heroes)
	}

	private fun HeroesRepository<HKImplementation>.stubThrow(throwable: Throwable) {
		every { getHeroes() } returns ObservableK.raiseError(throwable)
	}
}