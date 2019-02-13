package com.marko.presentation.sampledata

import com.marko.domain.entities.*
import com.marko.presentation.entities.Hero
import com.marko.presentation.entities.Heroes

fun heroEntity(
	id: String = "id",
	name: String = "name",
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

fun hero(
	id: String = "id",
	name: String = "name",
	description: String = "description",
	modified: String = "modified",
	resourceUri: String = "resourceUri",
	urls: List<MarvelUrl> = listOf(),
	thumbnail: MarvelImage = MarvelImage(path = "", extension = ""),
	comics: MarvelResources<ComicResource> = MarvelResources(),
	stories: MarvelResources<StoryResource> = MarvelResources(),
	events: MarvelResources<EventResource> = MarvelResources(),
	series: MarvelResources<SeriesResource> = MarvelResources()
): Hero = Hero(
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

val heroesEntity: HeroesEntity
	get() = listOf(
		heroEntity(),
		heroEntity(),
		heroEntity()
	)

val heroes: Heroes
	get() = listOf(
		hero(),
		hero(),
		hero()
	)