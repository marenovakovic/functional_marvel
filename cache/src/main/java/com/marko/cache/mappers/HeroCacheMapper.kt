package com.marko.cache.mappers

import com.marko.cache.entities.HeroCache
import com.marko.cache.entities.HeroesCache
import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData

/**
 * Mapping [HeroData] to [HeroCache]
 *
 * @receiver [HeroData] that is being mapped to [HeroCache]
 *
 * @return [HeroCache] mapped from [HeroData]
 */
fun HeroData.toCache(): HeroCache = HeroCache(
	id = id,
	name = name,
	description = description,
	modified = modified,
	resourceUri = resourceUri,
	urls = urls,
	thumbnail = thumbnail,
	comics = comics,
	stories = stories,
	events = events,
	series = series
)

/**
 * Mapping [HeroesData] to [HeroCache] [List]
 *
 * @receiver [HeroesData] that is being mapped to [HeroCache] [List]
 *
 * @return [HeroesCache] mapped from [HeroesData]
 */
fun HeroesData.toCache(): HeroesCache = map { it.toCache() }

/**
 * Mapping [HeroCache] to [HeroData]
 *
 * @receiver [HeroCache] that is being mapped to [HeroData]
 *
 * @return [HeroData] mapped from [HeroCache]
 */
fun HeroCache.toData(): HeroData = HeroData(
	id = id,
	name = name,
	description = description,
	modified = modified,
	resourceUri = resourceUri,
	urls = urls,
	thumbnail = thumbnail,
	comics = comics,
	stories = stories,
	events = events,
	series = series
)

/**
 * Mapping [HeroCache] [List] to [HeroesData]
 *
 * @receiver [HeroCache] [List] that is being mapped to [HeroesData]
 *
 * @return [HeroesData] mapped from [HeroesCache]
 */
fun HeroesCache.toData(): HeroesData = map { it.toData() }