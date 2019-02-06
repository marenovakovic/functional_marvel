package com.marko.functional_marvel.usecases

import arrow.effects.ObservableK
import arrow.effects.value
import com.marko.functional_marvel.domain.heroes.HeroesRepository
import com.marko.functional_marvel.injection.HKImplementation
import com.marko.functional_marvel.sampledata.hero
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

			setFavorite(hero).value().test()
				.assertComplete()

			verify(exactly = 1) { heroesRepository.saveHero(hero) }
		}
	}

	private fun HeroesRepository<HKImplementation>.stubSave() {
		every { saveHero(any()) } returns ObservableK.just(Unit)
	}
}