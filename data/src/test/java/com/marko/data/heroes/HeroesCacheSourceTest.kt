package com.marko.data.heroes

import arrow.core.Left
import arrow.core.Right
import com.marko.data.arrow.shouldBeLeft
import com.marko.data.arrow.shouldBeRight
import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.data.sampledata.heroData
import com.marko.data.sampledata.heroesData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class HeroesCacheSourceTest {

	private val heroesCacheRepository = mockk<HeroesCacheRepository>()
	private val heroesCacheSource = HeroesCacheSource(heroesCacheRepository = heroesCacheRepository)

	@Test
	fun `check is getHeroes result Right and is cache repository called`() = runBlocking {
		val heroes = heroesData

		heroesCacheRepository.stubHeroes(heroes)

		val result = heroesCacheSource.getHeroes()

		result shouldBeRight heroes
		coVerify(exactly = 1) { heroesCacheRepository.queryHeroes() }
	}

	@Test
	fun `check is getHeroes result Left when something goes wrong and is cache repository called`() =
		runBlocking {
			val t = Throwable("nem'm")

			heroesCacheRepository.stubThrow(t)

			val result = heroesCacheSource.getHeroes()

			result.shouldBeLeft(t)
			coVerify(exactly = 1) { heroesCacheRepository.queryHeroes() }
		}

	@Test
	fun `check is getHero result Right and is cache repository called`() = runBlocking {
		val heroId = "1"
		val hero = heroData(id = heroId)

		heroesCacheRepository.stubHero(hero)

		val result = heroesCacheSource.getHero(heroId = heroId)

		result shouldBeRight hero
		coVerify { heroesCacheRepository.queryHero(heroId = heroId) }
	}

	@Test
	fun `check is getHero result Left when something goes wrong and is cache repository called`() =
		runBlocking {
			val heroId = "1"
			val t = Throwable("nem'm")

			heroesCacheRepository.stubThrow(t)

			val result = heroesCacheSource.getHero(heroId = heroId)

			result.shouldBeLeft(t)
			coVerify(exactly = 1) { heroesCacheRepository.queryHero(heroId = heroId) }
		}

	@Test
	fun `check is saveHero result Right and is cache repository called`() = runBlocking {
		val hero = heroData()

		heroesCacheRepository.stubSave()

		val result = heroesCacheSource.saveHero(hero = hero)

		result shouldBeRight Unit
		coVerify(exactly = 1) { heroesCacheRepository.saveHero(hero) }
	}

	@Test
	fun `check is saveHero result Left when something goes wrong and is cache repository called`() =
		runBlocking {
			val hero = heroData()
			val t = Throwable("nem'm")

			heroesCacheRepository.stubThrow(t)

			val result = heroesCacheSource.saveHero(hero = hero)

			result.shouldBeLeft(t)
			coVerify(exactly = 1) { heroesCacheRepository.saveHero(hero) }
		}

	@Test
	fun `check is saveHeroes result Right and is cache repository called`() = runBlocking {
		val heroes = heroesData

		heroesCacheRepository.stubSave()

		val result = heroesCacheSource.saveHeroes(heroes = heroes)

		result shouldBeRight Unit
		coVerify(exactly = 1) { heroesCacheRepository.saveHeroes(heroes = heroes) }
	}

	@Test
	fun `check is saveHeroes result Left when something goes wrong and is cache repository called`() =
		runBlocking {
			val heroes = heroesData
			val t = Throwable("nem'm")

			heroesCacheRepository.stubThrow(t)

			val result = heroesCacheSource.saveHeroes(heroes = heroes)

			result.shouldBeLeft(t)
			coVerify(exactly = 1) { heroesCacheRepository.saveHeroes(heroes = heroes) }
		}

	@Test
	fun `does getFavorites calls cache repository`() = runBlocking {
		val heroes = heroesData

		heroesCacheRepository.stubHeroes(heroes)

		heroesCacheSource.getFavorites()

		coVerify(exactly = 1) { heroesCacheRepository.queryFavorites() }
	}

	@Test
	fun `is getFavorites result Right`() = runBlocking {
		val heroes = heroesData

		heroesCacheRepository.stubHeroes(heroes)

		val result = heroesCacheSource.getFavorites()

		result shouldBeRight heroes
	}

	@Test
	fun `is getFavorites result Left when something goes wrong`() = runBlocking {
		val t = Throwable("nem'm")

		heroesCacheRepository.stubThrow(t)

		val result = heroesCacheSource.getFavorites()

		result shouldBeLeft t
	}

	@Test
	fun `does setFavorite calls cache repository`() = runBlocking {
		val heroId = "1"

		heroesCacheRepository.stubSave()

		heroesCacheSource.setFavorite(heroId = heroId)

		coVerify(exactly = 1) { heroesCacheRepository.setFavorite(heroId = heroId) }
	}

	@Test
	fun `is setFavorite result Right`() = runBlocking {
		val heroId = "1"

		heroesCacheRepository.stubSave()

		val result = heroesCacheSource.setFavorite(heroId = heroId)

		result shouldBeRight Unit
	}

	@Test
	fun `is setFavorite result Left when something goes wrong`() = runBlocking {
		val t = Throwable("nemeze")

		heroesCacheRepository.stubThrow(t)

		val result = heroesCacheSource.setFavorite(heroId = "1")

		result shouldBeLeft t
	}

	@Test
	fun `does removeFavorite calls cache repository`() = runBlocking {
		val heroId = "1"

		heroesCacheRepository.stubRemoveFavorite()

		heroesCacheSource.removeFavorite(heroId = heroId)

		coVerify(exactly = 1) { heroesCacheRepository.removeFavorite(heroId = heroId) }
	}

	@Test
	fun `is removeFavorite result Right`() = runBlocking {
		heroesCacheRepository.stubRemoveFavorite()

		val result = heroesCacheSource.removeFavorite(heroId = "1")

		result shouldBeRight Unit
	}

	@Test
	fun `is removeFavorite result Left when something goes wrong`() = runBlocking {
		val t = Throwable("nemeze")

		heroesCacheRepository.stubThrow(t)

		val result = heroesCacheSource.removeFavorite(heroId = "1")

		result shouldBeLeft t
	}

	private fun HeroesCacheRepository.stubHeroes(heroes: HeroesData) {
		coEvery { queryHeroes() } returns Right(heroes)
		coEvery { queryFavorites() } returns Right(heroes)
	}

	private fun HeroesCacheRepository.stubHero(hero: HeroData) {
		coEvery { queryHero(any()) } returns Right(hero)
	}

	private fun HeroesCacheRepository.stubSave() {
		coEvery { saveHero(any()) } returns Right(Unit)
		coEvery { saveHeroes(any()) } returns Right(Unit)
		coEvery { setFavorite(any()) } returns Right(Unit)
	}

	private fun HeroesCacheRepository.stubRemoveFavorite() {
		coEvery { removeFavorite(any()) } returns Right(Unit)
	}

	private fun HeroesCacheRepository.stubThrow(t: Throwable) {
		coEvery { saveHero(any()) } returns Left(t)
		coEvery { saveHeroes(any()) } returns Left(t)
		coEvery { queryHero(any()) } returns Left(t)
		coEvery { queryHeroes() } returns Left(t)
		coEvery { queryFavorites() } returns Left(t)
		coEvery { setFavorite(any()) } returns Left(t)
		coEvery { removeFavorite(any()) } returns Left(t)
	}
}