package com.marko.data.mappers

import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.data.sampledata.heroData
import com.marko.data.sampledata.heroEntity
import com.marko.data.sampledata.heroesData
import com.marko.data.sampledata.heroesEntity
import com.marko.domain.entities.HeroEntity
import com.marko.domain.entities.HeroesEntity
import org.junit.jupiter.api.Test

internal class HeroDataMapperTest {

	@Test
	fun `test HeroEntity to HeroData mapping`() {
		val heroEntity = heroEntity()
		val heroData = heroEntity.toData()

		assertHeroes(heroEntity, heroData)
	}

	@Test
	fun `test HeroEntityList to HeroDataList mapping`() {
		val heroesEntity = heroesEntity
		val heroesData = heroesEntity.toData()

		assertHeroes(heroesEntity, heroesData)
	}

	@Test
	fun `test HeroData to HeroEntity mapping`() {
		val heroData = heroData()
		val heroEntity = heroData.toEntity()

		assertHeroes(heroEntity, heroData)
	}

	@Test
	fun `test HeroDataList to HeroEntityList mapping`() {
		val heroesData = heroesData
		val heroesEntity = heroesData.toEntity()

		assertHeroes(heroesEntity, heroesData)
	}

	private fun assertHeroes(heroesEntity: HeroesEntity, heroesData: HeroesData) {
		assert(heroesEntity.size == heroesData.size)
		repeat(heroesEntity.size) { assertHeroes(heroesEntity[it], heroesData[it]) }
	}

	private fun assertHeroes(heroEntity: HeroEntity, heroData: HeroData) {
		assert(heroEntity.id == heroData.id)
		assert(heroEntity.name == heroData.name)
		assert(heroEntity.description == heroData.description)
		assert(heroEntity.modified == heroData.modified)
		assert(heroEntity.resourceUri == heroData.resourceUri)
		assert(heroEntity.isFavorite == heroData.isFavorite)
		assert(heroEntity.urls == heroData.urls)
		assert(heroEntity.thumbnail == heroData.thumbnail)
		assert(heroEntity.comics == heroData.comics)
		assert(heroEntity.stories == heroData.stories)
		assert(heroEntity.events == heroData.events)
		assert(heroEntity.series == heroData.series)
	}
}