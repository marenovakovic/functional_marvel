package com.marko.functional_marvel.data.comics

import arrow.effects.DeferredK
import arrow.effects.ForDeferredK
import arrow.effects.deferredk.monadDefer.monadDefer
import arrow.effects.unsafeRunAsync
import arrow.effects.unsafeRunSync
import com.marko.functional_marvel.entities.Comics
import com.marko.functional_marvel.exceptions.ContentNotAvailable
import com.marko.functional_marvel.exceptions.MarvelException
import com.marko.functional_marvel.sampledata.sampleComics
import io.kotlintest.Description
import io.kotlintest.assertions.arrow.either.shouldBeRight
import io.kotlintest.specs.StringSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.assertThrows

internal class ComicsRepositoryImplTest : StringSpec() {

	override fun beforeTest(description: Description) = clearAllMocks()

	private val comicsDataSource = mockk<ComicsDataSource<ForDeferredK>>()
	private val comicsRepository =
		ComicsRepositoryImpl(
			monadDefer = DeferredK.monadDefer(),
			comicsDataSource = comicsDataSource
		)

	init {
		"check result and does repository calls data source" {
			val comics = sampleComics
			comicsDataSource.stubComics(comics = comics)

			comicsRepository.getComics().unsafeRunAsync {
				it.shouldBeRight(comics)
				verify(exactly = 1) { comicsDataSource.getComics() }
			}
		}

		"check result when fetching goes wrong" {
			val t = ContentNotAvailable
			comicsDataSource.stubThrow(t)

			val thrown =
				assertThrows<MarvelException> { comicsRepository.getComics().unsafeRunSync() }

			assert(thrown === t)
			verify(exactly = 1) { comicsDataSource.getComics() }
		}
	}

	private fun ComicsDataSource<*>.stubComics(comics: Comics) {
		every { getComics() } returns DeferredK.just(comics)
	}

	private fun ComicsDataSource<*>.stubThrow(t: Throwable) {
		every { getComics() } returns DeferredK.raiseError(t)
	}
}