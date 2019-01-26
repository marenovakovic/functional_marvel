package com.marko.functional_marvel.cache

import com.marko.functional_marvel.entities.HeroCache
import com.marko.functional_marvel.sampledata.heroCache
import com.marko.functional_marvel.sampledata.sampleDaoHeroes
import org.junit.Test

class HeroesDaoTest : DatabaseTest() {

	@Test
	fun `test saving and querying single hero`() {
		val hero = heroCache(id = "1")

		heroesDao.saveHero(hero)

		val savedHero = heroesDao.queryHero(hero.id)

		assertHero(hero, savedHero)
	}

	@Test
	fun `test saving and querying multiple heroes`() {
		val heroes = sampleDaoHeroes

		heroesDao.saveHeroes(heroes)

		val savedHeroes = heroesDao.queryAllHeroes()

		assertHeroes(heroes, savedHeroes)
	}

	private fun assertHeroes(heroes: List<HeroCache>, other: List<HeroCache>) {
		assert(heroes.size == other.size)
		repeat(heroes.size) { assertHero(heroes[it], other[it]) }
	}

	private fun assertHero(hero: HeroCache, other: HeroCache) {
		assert(hero.id == other.id)
		assert(hero.name == other.name)
		assert(hero.description == other.description)
	}
}