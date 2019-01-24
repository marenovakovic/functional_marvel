package com.marko.functional_marvel.mappers

import com.karumi.marvelapiclient.model.MarvelUrl
import com.marko.functional_marvel.entities.HeroUrl

/**
 * Mapping [MarvelUrl] to [HeroUrl]
 *
 * @receiver [MarvelUrl] that is being mapped to [HeroUrl]
 *
 * @return [HeroUrl] mapped from [MarvelUrl]
 */
fun MarvelUrl.toHeroUrl(): HeroUrl = HeroUrl(
	type = type,
	url = url
)

/**
 * Mapping [MarvelUrl] [List] to [HeroUrl] [List]
 *
 * @receiver [MarvelUrl] [List] that is being mapped to [HeroUrl] [List]
 *
 * @return [HeroUrl] [List] mapped from [MarvelUrl] [List]
 */
fun List<MarvelUrl>.toHeroUrl(): List<HeroUrl> = map { it.toHeroUrl() }