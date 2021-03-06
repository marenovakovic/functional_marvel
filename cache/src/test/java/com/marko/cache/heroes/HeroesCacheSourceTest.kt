//package com.marko.cache.heroes
//
//import com.marko.cache.entities.HeroCache
//import com.marko.cache.mappers.toCache
//import com.marko.domain.sampledata.hero
//import com.marko.domain.sampledata.sampleHeroes
//import io.mockk.every
//import io.mockk.mockk
//import org.junit.Test
//
//class HeroesCacheSourceTest {
//
//	private val heroesDao = mockk<HeroesDao>()
//
//	private val heroesCacheSource =
//		HeroesCacheSource(async = DeferredK.async(), heroesDao = heroesDao)
//
//	@Test
//	fun `test saving and querying single hero`() {
//		val hero = hero()
//
//		heroesDao.stubSave()
//		heroesDao.stubHero(hero.toCache())
//		heroesCacheSource.saveHero(hero)
//
//		val savedHero = heroesCacheSource.getHero(hero.id).unsafeRunSync()
//
//		assertHero(hero, savedHero)
//	}
//
//	@Test
//	fun `test saving and querying multiple heroes`() {
//		val heroes = sampleHeroes
//
//		heroesDao.stubSave()
//		heroesDao.stubHeroes(heroes.toCache())
//		heroesCacheSource.saveHeroes(heroes)
//
//		val savedHeroes = heroesCacheSource.getHeroes().unsafeRunSync()
//
//		assertHeroes(heroes, savedHeroes)
//	}
//
//	private fun assertHeroes(heroes: Heroes, other: Heroes) {
//		assert(heroes.size == other.size)
//		repeat(heroes.size) { assertHero(heroes[it], other[it]) }
//	}
//
//	private fun assertHero(hero: Hero, other: Hero) {
//		assert(hero.id == other.id)
//		assert(hero.name == other.name)
//		assert(hero.description == other.description)
//	}
//
//	private fun HeroesDao.stubHeroes(heroes: List<HeroCache>) {
//		every { queryAllHeroes() } returns heroes
//	}
//
//	private fun HeroesDao.stubHero(hero: HeroCache) {
//		every { queryHero(any()) } returns hero
//	}
//
//	private fun HeroesDao.stubSave() {
//		every { saveHero(any()) } returns Unit
//		every { saveHeroes(any()) } returns Unit
//	}
//}