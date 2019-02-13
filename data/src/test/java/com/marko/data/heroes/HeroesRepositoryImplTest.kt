package com.marko.data.heroes

import arrow.core.Left
import arrow.core.Right
import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.data.mappers.toData
import com.marko.data.mappers.toEntity
import com.marko.data.sampledata.heroData
import com.marko.data.sampledata.heroEntity
import com.marko.data.sampledata.heroesData
import com.marko.data.sampledata.heroesEntity
import io.kotlintest.assertions.arrow.either.shouldBeLeft
import io.kotlintest.assertions.arrow.either.shouldBeRight
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class HeroesRepositoryImplTest {

	private val heroesRemoteSource = mockk<HeroesDataSource>()
	private val heroesCacheSource = mockk<HeroesDataSource>()
	private val heroesRepository = HeroesRepositoryImpl(
		heroesRemoteSource = heroesRemoteSource,
		heroesCacheSource = heroesCacheSource
	)

	@Test
	fun `check is getHeroes result Right when cache fails and does it calls remote source`() =
		runBlocking {
			val t = Throwable("ne'm")
			heroesCacheSource.stubThrow(t)

			val heroes = heroesData
			heroesRemoteSource.stubHeroes(heroes)

			val result = heroesRepository.getHeroes().unsafeRunSync()

			result.shouldBeRight(heroes.toEntity())
			coVerify(exactly = 1) { heroesCacheSource.getHeroes() }
			coVerify(exactly = 1) { heroesRemoteSource.getHeroes() }
		}

	@Test
	fun `check is getHero result Right and does it calls data sources`() = runBlocking {
		val heroId = "1"
		val heroData = heroData(id = heroId)

		heroesCacheSource.stubHero(heroData)

		val result = heroesRepository.getHero(heroId = heroId)

		result.shouldBeRight(heroData.toEntity())
		coVerify(exactly = 1) { heroesCacheSource.getHero(heroId = heroId) }
		coVerify(exactly = 0) { heroesRemoteSource.getHero(heroId = heroId) }
	}

	@Test
	fun `check is getHero result Right when something goes wrong and does it call remote source`() =
		runBlocking {
			val t = Throwable("nem'm")
			val heroId = "1"
			val remoteHero = heroData(id = heroId)

			heroesCacheSource.stubThrow(t)
			heroesRemoteSource.stubHero(remoteHero)

			val result = heroesRepository.getHero(heroId = heroId)

			result.shouldBeRight(remoteHero.toEntity())
			coVerify(exactly = 1) { heroesCacheSource.getHero(heroId = heroId) }
			coVerify(exactly = 1) { heroesRemoteSource.getHero(heroId = heroId) }
		}

	@Test
	fun `check is getHero result Right when cache returns invalid hero and is remote source called`() =
		runBlocking {
			val heroId = "1"
			val cacheHero = heroData(id = "")
			val remoteHero = heroData(id = heroId)

			heroesCacheSource.stubHero(cacheHero)
			heroesRemoteSource.stubHero(remoteHero)

			val result = heroesRepository.getHero(heroId = heroId)

			result.shouldBeRight(remoteHero.toEntity())
			coVerify(exactly = 1) { heroesCacheSource.getHero(heroId = heroId) }
			coVerify(exactly = 1) { heroesRemoteSource.getHero(heroId = heroId) }
		}

	@Test
	fun `check is fetch result Right and does it calls data source`() = runBlocking {
		val stubHeroes = heroesData
		heroesCacheSource.stubHeroes(stubHeroes)

		val result = heroesRepository.getHeroes().unsafeRunSync()

		result.shouldBeRight(stubHeroes.toEntity())
		coVerify(exactly = 1) { heroesCacheSource.getHeroes() }
	}

	@Test
	fun `is remote data source called when cache data source returns empty list`() = runBlocking {
		val cacheHeroes = emptyList<HeroData>()
		val remoteHeroes = heroesData

		heroesCacheSource.stubHeroes(cacheHeroes)
		heroesRemoteSource.stubHeroes(remoteHeroes)

		val result = heroesRepository.getHeroes().unsafeRunSync()

		result.shouldBeRight(remoteHeroes.toEntity())
		coVerify(exactly = 1) { heroesRemoteSource.getHeroes() }
		coVerify(exactly = 1) { heroesCacheSource.getHeroes() }
	}

	@Test
	fun `does save calls cacheSource`() = runBlocking {
		heroesCacheSource.stubSave()

		val hero = heroEntity()

		val result = heroesRepository.saveHero(hero)

		result.shouldBeRight(Unit)
		coVerify(exactly = 1) { heroesCacheSource.saveHero(hero.toData()) }
		coVerify(exactly = 0) { heroesRemoteSource.saveHero(hero.toData()) }
	}

	@Test
	fun `does save heroes calls cacheSource`() = runBlocking {
		heroesCacheSource.stubSave()

		val heroes = heroesEntity

		val result = heroesRepository.saveHeroes(heroes)

		result.shouldBeRight(Unit)
		coVerify(exactly = 1) { heroesCacheSource.saveHeroes(heroes.toData()) }
		coVerify(exactly = 0) { heroesRemoteSource.saveHeroes(heroes.toData()) }
	}

	@Test
	fun `does getFavorites calls data source`() = runBlocking {
		val heroes = heroesData

		heroesCacheSource.stubFavorites(heroes)

		heroesRepository.getFavorites()

		coVerify(exactly = 1) { heroesCacheSource.getFavorites() }
	}

	@Test
	fun `check is getFavorites result Right`() = runBlocking {
		val heroes = heroesData

		heroesCacheSource.stubFavorites(heroes)

		heroesRepository.getFavorites().shouldBeRight(heroes.toEntity())
	}

	@Test
	fun `check is getFavorites result Left when something goes wrong`() = runBlocking {
		val t = Throwable("nem'm")

		heroesCacheSource.stubThrow(t)

		heroesRepository.getFavorites().shouldBeLeft(t)
	}

	private fun HeroesDataSource.stubHeroes(heroesData: HeroesData) {
		coEvery { getHeroes() } returns Right(heroesData)
	}

	private fun HeroesDataSource.stubHero(heroData: HeroData) {
		coEvery { getHero(any()) } returns Right(heroData)
	}

	private fun HeroesDataSource.stubSave() {
		coEvery { saveHero(any()) } returns Right(Unit)
		coEvery { saveHeroes(any()) } returns Right(Unit)
	}

	private fun HeroesDataSource.stubFavorites(favorites: HeroesData) {
		coEvery { getFavorites() } returns Right(favorites)
	}

	private fun HeroesDataSource.stubThrow(t: Throwable) {
		coEvery { getHero(any()) } returns Left(t)
		coEvery { getHeroes() } returns Left(t)
		coEvery { saveHero(any()) } returns Left(t)
		coEvery { saveHeroes(any()) } returns Left(t)
		coEvery { getFavorites() } returns Left(t)
	}
}