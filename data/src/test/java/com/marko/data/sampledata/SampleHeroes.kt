package com.marko.data.sampledata

import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.domain.entities.*

fun heroEntity(
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

fun heroData(
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
): HeroData = HeroData(
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

val heroesData: HeroesData
	get() = listOf(
		heroData(),
		heroData(),
		heroData()
	)