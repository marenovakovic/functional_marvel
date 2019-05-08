package com.marko.cache.heroes

import com.marko.cache.entities.HeroCache
import com.marko.cache.entities.HeroesCache
import com.marko.cache.mappers.toData
import com.marko.cache.sampledata.heroCache
import com.marko.cache.sampledata.sampleHeroesCache
import io.kotlintest.assertions.arrow.either.shouldBeLeft
import io.kotlintest.assertions.arrow.either.shouldBeRight
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class HeroesCacheRepositoryImplTest {

	private val heroesDao = mockk<HeroesDao>()
	private val heroesCacheRepository = HeroesCacheRepositoryImpl(heroesDao = heroesDao)

	@Test
	fun `check is saveHeroes result Right and does repository call DAO`() = runBlocking {
		val heroesCache = sampleHeroesCache

		heroesDao.stubSave()

		val result = heroesCacheRepository.saveHeroes(heroes = heroesCache.toData())

		result.shouldBeRight(Unit)
		coVerify(exactly = 1) { heroesDao.saveHeroes(heroesCache) }
	}

	@Test
	fun `check is saveHeroes result Left when something goes wrong`() = runBlocking {
		val t = Throwable("nem'm")

		heroesDao.stubThrow(t)

		val result = heroesCacheRepository.saveHeroes(listOf())

		result.shouldBeLeft(t)
		coVerify(exactly = 1) { heroesDao.saveHeroes(listOf()) }
	}

	@Test
	fun `check is saveHero result Right and does repository call DAO`() = runBlocking {
		val heroCache = heroCache()

		heroesDao.stubSave()

		val result = heroesCacheRepository.saveHero(hero = heroCache.toData())
		result.shouldBeRight(Unit)
		coVerify(exactly = 1) { heroesDao.saveHero(hero = heroCache) }
	}

	@Test
	fun `check is saveHero result Left when something goes wrong`() = runBlocking {
		val t = Throwable("nem'm")
		val heroCache = heroCache()

		heroesDao.stubThrow(t)

		val result = heroesCacheRepository.saveHero(heroCache.toData())

		result.shouldBeLeft(t)
		coVerify(exactly = 1) { heroesDao.saveHero(heroCache) }
	}

	@Test
	fun `check is queryHeroes result Right and does repository call DAO`() = runBlocking {
		val heroes = sampleHeroesCache
		heroesDao.stubHeroes(heroes)

		val result = heroesCacheRepository.queryHeroes()

		result.shouldBeRight(heroes.toData())
		coVerify(exactly = 1) { heroesDao.queryAllHeroes() }
	}

	@Test
	fun `check is queryHero result Right and does repository call DAO`() = runBlocking {
		val heroId = "1"
		val hero = heroCache(id = heroId)

		heroesDao.stubHero(hero)

		val result = heroesCacheRepository.queryHero(heroId = heroId)

		result.shouldBeRight(hero.toData())
		coVerify(exactly = 1) { heroesDao.queryHero(heroId = heroId) }
	}

	private fun HeroesDao.stubHeroes(heroes: HeroesCache) {
		coEvery { queryAllHeroes() } returns heroes
	}

	private fun HeroesDao.stubHero(hero: HeroCache) {
		coEvery { queryHero(any()) } returns hero
	}

	private fun HeroesDao.stubSave() {
		coEvery { saveHero(any()) } returns Unit
		coEvery { saveHeroes(any()) } returns Unit
	}

	private fun HeroesDao.stubThrow(t: Throwable) {
		coEvery { queryAllHeroes() } throws t
		coEvery { queryHero(any()) } throws t
		coEvery { saveHero(any()) } throws t
		coEvery { saveHeroes(any()) } throws t
	}
}