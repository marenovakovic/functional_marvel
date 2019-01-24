package com.marko.functional_marvel.usecases

import arrow.effects.DeferredK
import arrow.effects.unsafeRunSync
import com.marko.functional_marvel.domain.HeroesRepository
import com.marko.functional_marvel.hero
import com.marko.functional_marvel.injection.HKImplementation
import io.kotlintest.specs.StringSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

internal class SetFavoriteTest : StringSpec() {

	private val heroesRepository = mockk<HeroesRepository<HKImplementation>>()
	private val setFavorite = SetFavorite(heroesRepository = heroesRepository)

	init {
		"check result and does use case calls repository" {
			val hero = hero()

			heroesRepository.stubSave()

			setFavorite(hero).unsafeRunSync()

			verify(exactly = 1) { heroesRepository.saveHero(hero) }
		}
	}

	private fun HeroesRepository<HKImplementation>.stubSave() {
		every { saveHero(any()) } returns DeferredK.just(Unit)
	}
}