package com.marko.domain.sampledata

import com.marko.domain.entities.*

fun hero(
	id: String = "id",
	name: String = "name",
	isFavorite: Boolean = false,
	description: String = "description",
	modified: String = "modified",
	resourceUri: String = "resourceUri",
	urls: List<MarvelUrl> = listOf(),
	thumbnail: MarvelImage = MarvelImage(path = "", extension = ""),
	comics: MarvelResources<ComicResource> = MarvelResources(),
	stories: MarvelResources<StoryResource> = MarvelResources(),
	events: MarvelResources<EventResource> = MarvelResources(),
	series: MarvelResources<SeriesResource> = MarvelResources()
): HeroEntity = HeroEntity(
	id = id,
	name = name,
	isFavorite = isFavorite,
	description = description,
	modified = modified,
	resourceUri = resourceUri,
	urls = urls,
	thumbnail = thumbnail,
	comics = comics,
	series = series,
	events = events,
	stories = stories
)

val sampleHeroes: HeroesEntity
	get() = listOf(hero(), hero(), hero())