package com.marko.data.heroes

import arrow.core.Right
import com.marko.data.arrow.shouldBeLeft
import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.data.sampledata.heroData
import com.marko.data.sampledata.heroesData
import com.marko.domain.exceptions.UnsupportedCaching
import io.kotlintest.assertions.arrow.either.shouldBeRight
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class HeroesRemoteSourceTest {

	private val heroesRemoteRepository = mockk<HeroesRemoteRepository>()
	private val heroesRemoteSource =
		HeroesRemoteSource(heroesRemoteRepository = heroesRemoteRepository)

	@Test
	fun `check is getHeroes result Right`() = runBlocking {
		val stubHeroes = heroesData
		heroesRemoteRepository.stubHeroes(stubHeroes)

		val result = heroesRemoteSource.getHeroes()

		result.shouldBeRight(stubHeroes)
		coVerify(exactly = 1) { heroesRemoteRepository.fetchHeroes() }
	}

	@Test
	fun `check is getHero result Right and does it calls repository`() = runBlocking {
		val heroId = "1"
		val hero = heroData(id = heroId)

		heroesRemoteRepository.stubHero(hero)

		val result = heroesRemoteSource.getHero(heroId = heroId)

		result.shouldBeRight(hero)
		coVerify(exactly = 1) { heroesRemoteRepository.fetchHero(heroId = heroId) }
	}

	@Test
	fun `check is saveHero result Left(UnsupportedCaching)`() = runBlocking {
		val heroes = heroesData

		val result = heroesRemoteSource.saveHeroes(heroes)

		result.shouldBeLeft(UnsupportedCaching)
	}

	@Test
	fun `check does saveHeroes returns Left(UnsupportedCaching)`() = runBlocking {
		val hero = heroData()

		val result = heroesRemoteSource.saveHero(hero)

		result.shouldBeLeft(UnsupportedCaching)
	}

	@Test
	fun `is getFavorites result Left(UnsupportedCaching)`() = runBlocking {
		val result = heroesRemoteSource.getFavorites()

		result shouldBeLeft UnsupportedCaching
	}

	@Test
	fun `is setFavorite result Left(UnsupportedCaching)`() = runBlocking {
		val result = heroesRemoteSource.setFavorite(heroId = "1")

		result shouldBeLeft UnsupportedCaching
	}

	@Test
	fun `is removeFavorite result Left(UnsupportedCaching)`() = runBlocking {
		val result = heroesRemoteSource.removeFavorite(heroId = "1")

		result shouldBeLeft UnsupportedCaching
	}

	private fun HeroesRemoteRepository.stubHeroes(heroesData: HeroesData) {
		coEvery { fetchHeroes() } returns Right(heroesData)
	}

	private fun HeroesRemoteRepository.stubHero(heroData: HeroData) {
		coEvery { fetchHero(any()) } returns Right(heroData)
	}
}