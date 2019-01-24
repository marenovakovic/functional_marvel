package com.marko.functional_marvel.domain

import arrow.effects.DeferredK
import arrow.effects.unsafeRunAsync
import com.marko.functional_marvel.entities.Heroes
import com.marko.functional_marvel.injection.HKImplementation
import com.marko.functional_marvel.sampleHeroes
import com.marko.functional_marvel.usecases.FetchHeroes
import com.marko.functional_marvel.usecases.invoke
import io.kotlintest.Description
import io.kotlintest.assertions.arrow.either.shouldBeLeft
import io.kotlintest.assertions.arrow.either.shouldBeRight
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
		val fetchHeroes = FetchHeroes(heroesRepository)

		"check result and does use case calls repository" {

			val stubHeroes = sampleHeroes
			heroesRepository.stubHeroes(stubHeroes)

			fetchHeroes().unsafeRunAsync {
				verify(exactly = 1) { heroesRepository.getHeroes() }
				it.shouldBeRight(stubHeroes)
			}
		}

		"check result when something goes wrong" {
			val t = Throwable("jeb' se")
			heroesRepository.stubThrow(t)

			fetchHeroes().unsafeRunAsync {
				verify(exactly = 1) { heroesRepository.getHeroes() }
				it.shouldBeLeft(t)
			}
		}
	}

	private fun HeroesRepository<HKImplementation>.stubHeroes(heroes: Heroes) {
		every { getHeroes() } returns DeferredK.just(heroes)
	}

	private fun HeroesRepository<HKImplementation>.stubThrow(throwable: Throwable) {
		every { getHeroes() } returns DeferredK.raiseError(throwable)
	}
}