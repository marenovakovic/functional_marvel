package com.marko.cache.sampledata

import com.marko.cache.entities.HeroCache
import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.domain.entities.*

fun heroCache(
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
): HeroCache = HeroCache(
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

val sampleHeroesCache: List<HeroCache>
	get() = listOf(
		heroCache(),
		heroCache(),
		heroCache()
	)

// with different ids so they will not replace each other when inserting into database
val sampleDaoHeroes: List<HeroCache>
	get() = listOf(
		heroCache(id = "1"),
		heroCache(id = "2"),
		heroCache(id = "3")
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

val heroesData: HeroesData
	get() = listOf(
		heroData(),
		heroData(),
		heroData()
	)