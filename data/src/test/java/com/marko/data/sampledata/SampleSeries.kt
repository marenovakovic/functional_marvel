package com.marko.data.sampledata

import com.marko.data.entities.SeriesData
import com.marko.domain.entities.*

fun seriesEntity(
	id: String = "",
	title: String = "",
	description: String = "",
	resourceURI: String = "",
	urls: List<MarvelUrl> = emptyList(),
	startYear: Int = - 1,
	endYear: Int = - 1,
	rating: String = "",
	modified: String = "",
	thumbnail: MarvelImage = MarvelImage(path = "", extension = "", imageUrl = ""),
	comics: MarvelResources<ComicResource> = MarvelResources(),
	stories: MarvelResources<StoryResource> = MarvelResources(),
	events: MarvelResources<EventResource> = MarvelResources(),
	characters: MarvelResources<HeroResource> = MarvelResources(),
	creators: MarvelResources<CreatorResource> = MarvelResources(),
	next: MarvelResource = SeriesResource(resourceUrl = "", name = ""),
	previous: MarvelResource = SeriesResource(resourceUrl = "", name = "")
): SeriesEntity = SeriesEntity(
	id = id,
	title = title,
	description = description,
	resourceURI = resourceURI,
	urls = urls,
	startYear = startYear,
	endYear = endYear,
	rating = rating,
	modified = modified,
	thumbnail = thumbnail,
	comics = comics,
	stories = stories,
	events = events,
	characters = characters,
	creators = creators,
	next = next,
	previous = previous
)

val seriesEntities: List<SeriesEntity> = listOf(seriesEntity(), seriesEntity(), seriesEntity())

fun seriesData(
	id: String = "",
	title: String = "",
	description: String = "",
	resourceURI: String = "",
	urls: List<MarvelUrl> = emptyList(),
	startYear: Int = - 1,
	endYear: Int = - 1,
	rating: String = "",
	modified: String = "",
	thumbnail: MarvelImage = MarvelImage(path = "", extension = "", imageUrl = ""),
	comics: MarvelResources<ComicResource> = MarvelResources(),
	stories: MarvelResources<StoryResource> = MarvelResources(),
	events: MarvelResources<EventResource> = MarvelResources(),
	characters: MarvelResources<HeroResource> = MarvelResources(),
	creators: MarvelResources<CreatorResource> = MarvelResources(),
	next: MarvelResource = SeriesResource(resourceUrl = "", name = ""),
	previous: MarvelResource = SeriesResource(resourceUrl = "", name = "")
): SeriesData = SeriesData(
	id = id,
	title = title,
	description = description,
	resourceURI = resourceURI,
	urls = urls,
	startYear = startYear,
	endYear = endYear,
	rating = rating,
	modified = modified,
	thumbnail = thumbnail,
	comics = comics,
	stories = stories,
	events = events,
	characters = characters,
	creators = creators,
	next = next,
	previous = previous
)

val seriesDatas: List<SeriesData> = listOf(seriesData(), seriesData(), seriesData())