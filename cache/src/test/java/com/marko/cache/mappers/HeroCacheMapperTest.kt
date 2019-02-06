package com.marko.cache.mappers

import com.marko.cache.entities.HeroCache
import com.marko.cache.sampledata.heroCache
import com.marko.cache.sampledata.heroData
import com.marko.cache.sampledata.heroesData
import com.marko.cache.sampledata.sampleHeroesCache
import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import org.junit.jupiter.api.Test

internal class HeroCacheMapperTest {

	@Test
	fun `test HeroData to HeroCache mapping`() {
		val heroData = heroData()
		val heroCache = heroData.toCache()

		heroData shouldEqual heroCache
	}

	@Test
	fun `test HeroesData to HeroesCache mapping`() {
		val heroesData = heroesData
		val heroCaches = heroesData.toCache()

		heroesData shouldEqual heroCaches
	}

	@Test
	fun `test HeroCache to HeroData mapping`() {
		val heroCache = heroCache()
		val heroData = heroCache.toData()

		heroData shouldEqual heroCache
	}

	@Test
	fun `test HeroesCache to HeroesData mapping`() {
		val heroesCache = sampleHeroesCache
		val heroesData = heroesCache.toData()

		heroesData shouldEqual heroesCache
	}

	private infix fun HeroData.shouldEqual(heroCache: HeroCache) {
		assert(id == heroCache.id)
		assert(name == heroCache.name)
		assert(description == heroCache.description)
	}

	private infix fun HeroesData.shouldEqual(heroesCache: List<HeroCache>) {
		assert(size == heroesCache.size)
		forEachIndexed { index, hero -> hero shouldEqual heroesCache[index] }
	}
}