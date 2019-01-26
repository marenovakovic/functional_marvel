package com.marko.functional_marvel.mappers

import com.karumi.marvelapiclient.model.MarvelResources
import com.marko.functional_marvel.entities.HeroResource

/**
 * Mapping [MarvelResources] to [HeroResource]
 *
 * @receiver [MarvelResources] that is being mapped to [HeroResource]
 *
 * @return [HeroResource] mapped from [MarvelResources]
 */
fun <T> MarvelResources<T>.toResource(): HeroResource<T> = HeroResource(
	available = available,
	returned = returned,
	collectionUri = collectionUri,
	items = items
)

/**
 * Mapping [MarvelResources] [List] to [HeroResource] [List]
 *
 * @receiver [MarvelResources] [List] that is being mapped to [HeroResource] [List]
 *
 * @return [HeroResource] [List] mapped from [MarvelResources] [List]
 */
fun <T> List<MarvelResources<T>>.toResource(): List<HeroResource<T>> =
	map { it.toResource() }