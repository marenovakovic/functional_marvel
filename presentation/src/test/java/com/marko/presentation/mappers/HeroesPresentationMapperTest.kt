package com.marko.presentation.mappers

import com.marko.domain.entities.HeroEntity
import com.marko.domain.entities.HeroesEntity
import com.marko.presentation.sampledata.hero
import com.marko.presentation.entities.Hero
import com.marko.presentation.entities.Heroes
import com.marko.presentation.sampledata.heroEntity
import com.marko.presentation.sampledata.heroes
import com.marko.presentation.sampledata.heroesEntity
import org.junit.jupiter.api.Test

internal class HeroesPresentationMapperTest {

	@Test
	fun `test HeroEntity to Hero mapping`() {
		val heroEntity = heroEntity()
		val hero = heroEntity.toPresentation()

		hero shouldEqual heroEntity
	}

	@Test
	fun `test HeroesEntity to Heroes mapping`() {
		val heroesEntity = heroesEntity
		val heroes = heroesEntity.toPresentation()

		heroes shouldEqual heroesEntity
	}

	@Test
	fun `test Hero to HeroEntity mapping`() {
		val hero = hero()
		val heroEntity = hero.toEntity()

		heroEntity shouldEqual hero
	}

	@Test
	fun `test Heroes to HeroesEntity mapping`() {
		val heroes = heroes
		val heroesEntity = heroes.toEntity()

		heroes shouldEqual heroesEntity
	}

	private infix fun Hero.shouldEqual(heroEntity: HeroEntity) = assertHeroes(heroEntity, this)

	private infix fun Heroes.shouldEqual(heroesEntity: HeroesEntity) = assertHeroes(heroesEntity, this)

	private infix fun HeroEntity.shouldEqual(hero: Hero) = assertHeroes(this, hero)

//	private infix fun HeroesEntity.shouldEqual(heroes: Heroes) = assertHeroes(this, heroes)

	private fun assertHeroes(heroesEntity: HeroesEntity, heroes: Heroes) {
		assert(heroesEntity.size == heroes.size)
		repeat(heroesEntity.size) { assertHeroes(heroesEntity[it], heroes[it]) }
	}

	private fun assertHeroes(heroEntity: HeroEntity, hero: Hero) {
		assert(heroEntity.id == hero.id)
		assert(heroEntity.name == hero.name)
		assert(heroEntity.description == hero.description)
		assert(heroEntity.modified == hero.modified)
		assert(heroEntity.resourceUri == hero.resourceUri)
		assert(heroEntity.isFavorite == hero.isFavorite)
		assert(heroEntity.urls == hero.urls)
		assert(heroEntity.thumbnail == hero.thumbnail)
		assert(heroEntity.comics == hero.comics)
		assert(heroEntity.stories == hero.stories)
		assert(heroEntity.events == hero.events)
		assert(heroEntity.series == hero.series)
	}
}