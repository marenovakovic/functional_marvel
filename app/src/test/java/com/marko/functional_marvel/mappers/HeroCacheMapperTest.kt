package com.marko.functional_marvel.mappers

import com.marko.functional_marvel.entities.Hero
import com.marko.functional_marvel.entities.HeroCache
import com.marko.functional_marvel.entities.Heroes
import com.marko.functional_marvel.sampledata.hero
import com.marko.functional_marvel.sampledata.heroCache
import com.marko.functional_marvel.sampledata.sampleHeroes
import com.marko.functional_marvel.sampledata.sampleHeroesCache
import io.kotlintest.specs.StringSpec

internal class HeroCacheMapperTest : StringSpec() {

	init {
		"test Hero to HeroCache mapping" {
			val hero = hero()
			val heroCache = hero.toCache()

			hero shouldEqual heroCache
		}

		"test Heroes to HeroCache list mapping" {
			val heroes = sampleHeroes
			val heroCaches = heroes.toCache()

			heroes shouldEqual heroCaches
		}

		"test HeroCache to Hero mapping" {
			val heroCache = heroCache()
			val hero = heroCache.toHero()

			hero shouldEqual heroCache
		}

		"test HeroCache to Heroes list mapping" {
			val heroCaches = sampleHeroesCache
			val heroes = heroCaches.toHeroes()

			heroes shouldEqual heroCaches
		}
	}

	private infix fun Hero.shouldEqual(heroCache: HeroCache) {
		assert(id == heroCache.id)
		assert(name == heroCache.name)
		assert(description == heroCache.description)
	}

	private infix fun Heroes.shouldEqual(heroesCache: List<HeroCache>) {
		assert(size == heroesCache.size)
		forEachIndexed { index, hero -> hero shouldEqual heroesCache[index] }
	}
}