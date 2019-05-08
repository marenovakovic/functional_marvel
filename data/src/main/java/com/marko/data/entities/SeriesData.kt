package com.marko.data.entities

import com.marko.domain.entities.*

/**
 * Data layer Series representation
 */
data class SeriesData(
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
)