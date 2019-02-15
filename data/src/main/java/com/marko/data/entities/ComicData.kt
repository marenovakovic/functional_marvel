package com.marko.data.entities

import com.marko.domain.entities.*

/**
 * Domain layer comic representation
 */
data class ComicData(
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
	val urls: List<MarvelUrl>,
	val series: ComicResource,
	val variants: List<MarvelResource>,
	val collections: List<MarvelCollection<*>>,
	val collectedIssues: List<MarvelCollection<*>>,
	val dates: List<MarvelDate>,
	val prices: List<MarvelPrice>,
	val thumbnail: MarvelImage,
	val images: List<MarvelImage>,
	val creators: MarvelResources<CreatorResource>,
	val characters: MarvelResources<HeroResource>,
	val stories: MarvelResources<StoryResource>,
	val events: MarvelResources<EventResource>
)