package com.marko.presentation.entities

import com.marko.domain.entities.*

data class Hero(
	val id: String,
	val name: String,
	val description: String,
	val modified: String,
	val resourceUri: String,
	val isFavorite: Boolean = false,
	val urls: List<MarvelUrl>,
	val thumbnail: MarvelImage,
	val comics: MarvelResources<ComicResource>,
	val stories: MarvelResources<StoryResource>,
	val events: MarvelResources<EventResource>,
	val series: MarvelResources<SeriesResource>
) : MarvelItem(itemId = id, imageUrl = thumbnail.imageUrl, itemTitle = name)