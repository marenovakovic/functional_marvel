package com.marko.presentation.entities

import com.marko.domain.entities.*

/**
 * Presentation layer series representation
 */
data class Series(
	val id: String,
	val title: String,
	val description: String,
	val resourceURI: String,
	val urls: List<MarvelUrl>,
	val startYear: Int,
	val endYear: Int,
	val rating: String,
	val modified: String,
	val thumbnail: MarvelImage,
	val comics: MarvelResources<ComicResource>,
	val stories: MarvelResources<StoryResource>,
	val events: MarvelResources<EventResource>,
	val characters: MarvelResources<HeroResource>,
	val creators: MarvelResources<CreatorResource>,
	val next: MarvelResource,
	val previous: MarvelResource
) : MarvelItem(itemId = id, imageUrl = thumbnail.imageUrl, itemTitle = title)