package com.marko.functional_marvel

import com.karumi.marvelapiclient.model.ComicResourceDto
import com.karumi.marvelapiclient.model.EventResourceDto
import com.karumi.marvelapiclient.model.SerieResourceDto
import com.karumi.marvelapiclient.model.StoryResourceDto
import com.marko.functional_marvel.entities.*

fun hero(
	id: String = "id",
	name: String = "name",
	description: String = "description",
	modified: String = "modified",
	resourceUri: String = "resourceUri",
	urls: List<HeroUrl> = listOf(),
	thumbnail: HeroImage = HeroImage(),
	comics: HeroResource<ComicResourceDto> = HeroResource(),
	stories: HeroResource<StoryResourceDto> = HeroResource(),
	events: HeroResource<EventResourceDto> = HeroResource(),
	series: HeroResource<SerieResourceDto> = HeroResource()
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

val sampleHeroes: Heroes
	get() = listOf(
		hero(),
		hero(),
		hero()
	)

fun heroCache(
	id: String = "id",
	name: String = "name",
	description: String = "description",
	modified: String = "modified",
	resourceUri: String = "resourceUri",
	urls: List<HeroUrl> = listOf(),
	thumbnail: HeroImage = HeroImage(),
	comics: HeroResource<ComicResourceDto> = HeroResource(),
	stories: HeroResource<StoryResourceDto> = HeroResource(),
	events: HeroResource<EventResourceDto> = HeroResource(),
	series: HeroResource<SerieResourceDto> = HeroResource()
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