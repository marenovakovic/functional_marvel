package com.marko.functional_marvel.data.heroes

import arrow.effects.*
import arrow.effects.deferredk.monadDefer.monadDefer
import com.marko.functional_marvel.entities.Heroes
import com.marko.functional_marvel.exceptions.ContentNotAvailable
import com.marko.functional_marvel.exceptions.MarvelException
import com.marko.functional_marvel.sampledata.hero
import com.marko.functional_marvel.sampledata.sampleHeroes
import io.kotlintest.Description
import io.kotlintest.assertions.arrow.either.shouldBeRight
import io.kotlintest.specs.StringSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.assertThrows

internal class HeroesRepositoryImplTest : StringSpec() {

	override fun beforeTest(description: Description) = clearAllMocks()

	init {
		val heroesRemoteSource = mockk<HeroesDataSource<ForDeferredK>>()
		val heroesCacheSource = mockk<HeroesDataSource<ForDeferredK>>()
		val heroesRepository =
			HeroesRepositoryImpl(
				DeferredK.monadDefer(),
				heroesRemoteSource,
				heroesCacheSource
			)

		"verify that execution is delayed" {
			heroesRepository.getHeroes().fix()

			verify(exactly = 0) { heroesRemoteSource.getHeroes() }
		}

		"check result and is remote source called" {
			val stubHeroes = sampleHeroes
			heroesRemoteSource.stubHeroes(stubHeroes)
			heroesCacheSource.stubHeroes(stubHeroes)

			heroesRepository.getHeroes().unsafeRunAsync {
				it.shouldBeRight()
				it.fold(
					{},
					{ assert(it.map { it.id } == stubHeroes.map { it.id }) }
				)
				verify(exactly = 1) { heroesRemoteSource.getHeroes() }
				verify(exactly = 1) { heroesCacheSource.getHeroes() }
			}
		}

		"check result when remote fetch goes wrong" {
			val t = Throwable("jeb' se")
			heroesRemoteSource.stubThrow(t)

			val stubHeroes = sampleHeroes
			heroesCacheSource.stubHeroes(stubHeroes)

			val thrown = assertThrows<MarvelException> {
				heroesRepository.getHeroes().unsafeRunSync()
			}

			assert(thrown is ContentNotAvailable)
			verify(exactly = 1) { heroesRemoteSource.getHeroes() }
			verify(exactly = 1) { heroesCacheSource.getHeroes() }
		}

		"check result when cache fetch goes wrong" {
			val t = Throwable("jeb' se")
			heroesCacheSource.stubThrow(t)

			val stubHeroes = sampleHeroes
			heroesRemoteSource.stubHeroes(stubHeroes)

			val thrown = assertThrows<MarvelException> {
				heroesRepository.getHeroes().unsafeRunSync()
			}

			assert(thrown is ContentNotAvailable)
			verify(exactly = 1) { heroesRemoteSource.getHeroes() }
			verify(exactly = 1) { heroesCacheSource.getHeroes() }
		}

		"check are favorite heroes mapped correctly" {
			val fetchedHeroes = listOf(
				hero(id = "1"),
				hero(id = "2"),
				hero(id = "3")
			)
			val cachedHeroes = listOf(
				hero(id = "1"),
				hero(id = "2"),
				hero(id = "23")
			)

			heroesRemoteSource.stubHeroes(fetchedHeroes)
			heroesCacheSource.stubHeroes(cachedHeroes)

			heroesRepository.getHeroes().unsafeRunAsync {
				it.shouldBeRight()
				it.fold(
					{},
					{ assert(it.filter { it.isFavorite }.size == 2) }
				)
				verify(exactly = 1) { heroesRemoteSource.getHeroes() }
				verify(exactly = 1) { heroesCacheSource.getHeroes() }
			}
		}

		"check saving and does it calls heroes cache data source" {
			heroesCacheSource.stubSave()

			val hero = hero()
			heroesRepository.saveHero(hero).unsafeRunAsync {
				it.shouldBeRight(Unit)
				verify(exactly = 1) { heroesCacheSource.saveHero(hero) }
			}
		}

		"check saving when error occurs and does it calls heroes cache data source" {
			val t = Throwable("unable to save")
			heroesCacheSource.stubSaveThrow(t)

			val hero = hero()
			val thrown = assertThrows<Throwable> {
				heroesRepository.saveHero(hero).unsafeRunSync()
			}

			assert(thrown.message == t.message)
			verify(exactly = 1) { heroesCacheSource.saveHero(hero) }
		}
	}

	private fun HeroesDataSource<*>.stubHeroes(heroes: Heroes) {
		every { getHeroes() } returns DeferredK.just(heroes)
	}

	private fun HeroesDataSource<*>.stubSave() {
		every { saveHero(any()) } returns DeferredK.just(Unit)
		every { saveHeroes(any()) } returns DeferredK.just(Unit)
	}

	private fun HeroesDataSource<*>.stubSaveThrow(t: Throwable) {
		every { saveHero(any()) } returns DeferredK.raiseError(t)
		every { saveHeroes(any()) } returns DeferredK.raiseError(t)
	}

	private fun HeroesDataSource<*>.stubThrow(t: Throwable) {
		every { getHeroes() } returns DeferredK.raiseError(t)
	}
}