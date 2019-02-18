package com.marko.domain.entities

/**
 * Domain layer Story representation
 */
data class StoryEntity(
	val id: Int,
	val title: String,
	val description: String,
	val resourceURI: String,
	val type: String,
	val modified: String,
	val thumbnail: MarvelImage,
	val comics: List<ComicResource>,
	val series: List<SeriesResource>,
	val events: List<EventResource>,
	val characters: List<HeroResource>,
	val creators: List<CreatorResource>
)