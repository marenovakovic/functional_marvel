package com.marko.domain.sampledata

import com.marko.domain.entities.*

fun hero(
	id: String = "id",
	name: String = "name",
	description: String = "description",
	modified: String = "modified",
	resourceUri: String = "resourceUri",
	urls: List<MarvelUrl> = listOf(),
	thumbnail: MarvelImage = MarvelImage(),
	comics: MarvelResources<ComicResource> = MarvelResources(),
	stories: MarvelResources<StoryResource> = MarvelResources(),
	events: MarvelResources<EventResource> = MarvelResources(),
	series: MarvelResources<SeriesResource> = MarvelResources()
): HeroEntity = HeroEntity(
	id = id,
	name = name,
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
	get() = listOf(
		hero(),
		hero(),
		hero()
	)