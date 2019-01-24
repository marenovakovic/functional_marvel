package com.marko.functional_marvel.mappers

import com.marko.functional_marvel.entities.Hero
import com.marko.functional_marvel.entities.HeroCache
import com.marko.functional_marvel.entities.Heroes

/**
 * Mapping [Hero] to [HeroCache]
 *
 * @receiver [Hero] that is being mapped to [HeroCache]
 *
 * @return [HeroCache] mapped from [Hero]
 */
fun Hero.toCache(): HeroCache = HeroCache(
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
 * Mapping [Heroes] to [HeroCache] [List]
 *
 * @receiver [Heroes] that is being mapped to [HeroCache] [List]
 *
 * @return [HeroCache] [List] mapped from [Heroes]
 */
fun Heroes.toCache(): List<HeroCache> = map { it.toCache() }

/**
 * Mapping [HeroCache] to [Heroes]
 *
 * @receiver [HeroCache] that is being mapped to [Hero]
 *
 * @return [Hero] mapped from [HeroCache]
 */
fun HeroCache.toHero(): Hero = Hero(
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
 * Mapping [HeroCache] [List] to [Heroes]
 *
 * @receiver [HeroCache] [List] that is being mapped to [Heroes]
 *
 * @return [Heroes] mapped from [HeroCache] [List]
 */
fun List<HeroCache>.toHeroes(): Heroes = map { it.toHero() }