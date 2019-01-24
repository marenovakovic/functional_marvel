package com.marko.functional_marvel.mappers

import com.marko.functional_marvel.entities.Hero
import com.marko.functional_marvel.entities.HeroCache
import com.marko.functional_marvel.hero
import com.marko.functional_marvel.heroCache
import com.marko.functional_marvel.sampleHeroes
import com.marko.functional_marvel.sampleHeroesCache
import io.kotlintest.specs.StringSpec

internal class HeroCacheMapperTest : StringSpec() {

	init {
		"test Hero to HeroCache mapping" {
			val hero = hero()
			val heroCache = hero.toCache()

			assertHeroes(hero, heroCache)
		}

		"test Heroes to HeroCache list mapping" {
			val heroes = sampleHeroes
			val heroCaches = heroes.toCache()

			assert(heroes.size == heroCaches.size)
			repeat(heroes.size) { assertHeroes(heroes[it], heroCaches[it]) }
		}

		"test HeroCache to Hero mapping" {
			val heroCache = heroCache()
			val hero = heroCache.toHero()

			assertHeroes(hero, heroCache)
		}

		"test HeroCache to Heroes list mapping" {
			val heroCaches = sampleHeroesCache
			val heroes = heroCaches.toHeroes()

			assert(heroes.size == heroCaches.size)
			repeat(heroes.size) { assertHeroes(heroes[it], heroCaches[it]) }
		}
	}

	private fun assertHeroes(hero: Hero, heroCache: HeroCache) {
		assert(hero.id == heroCache.id)
		assert(hero.name == heroCache.name)
		assert(hero.description == heroCache.description)
	}
}