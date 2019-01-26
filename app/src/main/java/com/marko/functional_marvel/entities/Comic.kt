package com.marko.functional_marvel.entities

import com.karumi.marvelapiclient.model.*

data class Comic(
	val id: String,
	val digitalId: Int,
	val title: String,
	val issueNumber: Double,
	val variantDescription: String,
	val description: String,
	val modified: String,
	val isbn: String,
	val upc: String,
	val diamondCode: String,
	val ean: String,
	val issn: String,
	val format: String,
	val pageCount: Int,
	val textObjects: List<TextObject>,
	val resourceURI: String,
	val urls: List<HeroUrl>,
	val series: ComicResourceDto,
	val variants: List<HeroResource<*>>,
	val collections: List<MarvelCollection<*>>,
	val collectedIssues: List<MarvelCollection<*>>,
	val dates: List<MarvelDate>,
	val prices: List<MarvelPrice>,
	val thumbnail: MarvelImage,
	val images: List<MarvelImage>,
	val creators: HeroResource<CreatorResourceDto>,
	val characters: HeroResource<CharacterResourceDto>,
	val stories: HeroResource<StoryResourceDto>,
	val events: HeroResource<EventResourceDto>
)