package com.marko.functional_marvel.usecases

import arrow.effects.DeferredK
import arrow.effects.unsafeRunAsync
import com.marko.functional_marvel.domain.comics.ComicsRepository
import com.marko.functional_marvel.entities.Comics
import com.marko.functional_marvel.injection.HKImplementation
import com.marko.functional_marvel.sampledata.sampleComics
import io.kotlintest.Description
import io.kotlintest.assertions.arrow.either.shouldBeLeft
import io.kotlintest.assertions.arrow.either.shouldBeRight
import io.kotlintest.specs.StringSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

internal class FetchComicsTest : StringSpec() {

	private val comicsRepository = mockk<ComicsRepository<HKImplementation>>()
	private val fetchComics = FetchComics(comicsRepository = comicsRepository)

	override fun beforeTest(description: Description) {
		clearAllMocks()
	}

	init {
		"check result and is comics repository called" {
			val stubComics = sampleComics
			comicsRepository.stubComics(stubComics)

			fetchComics().unsafeRunAsync {
				it.shouldBeRight(stubComics)
				verify(exactly = 1) { comicsRepository.getComics() }
			}
		}

		"check result when something goes wrong" {
			val t = Throwable("jeb' se")
			comicsRepository.stubThrow(t)

			fetchComics().unsafeRunAsync {
				it.shouldBeLeft(t)
				verify(exactly = 1) { comicsRepository.getComics() }
			}
		}
	}

	private fun ComicsRepository<*>.stubComics(comics: Comics) {
		every { getComics() } returns DeferredK.just(comics)
	}

	private fun ComicsRepository<*>.stubThrow(t: Throwable) {
		every { getComics() } returns DeferredK.raiseError(t)
	}
}