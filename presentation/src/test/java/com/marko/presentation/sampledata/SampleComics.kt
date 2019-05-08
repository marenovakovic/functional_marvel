package com.marko.presentation.sampledata

import com.marko.domain.entities.*
import com.marko.presentation.entities.Comic

fun comicEntity(
	id: String = "",
	digitalId: Int = - 1,
	title: String = "",
	issueNumber: Double = - 1.0,
	variantDescription: String = "",
	description: String = "",
	modified: String = "",
	isbn: String = "",
	upc: String = "",
	diamondCode: String = "",
	ean: String = "",
	issn: String = "",
	format: String = "",
	pageCount: Int = - 1,
	textObjects: List<TextObject> = listOf(),
	resourceURI: String = "",
	urls: List<MarvelUrl> = listOf(),
	series: ComicResource = ComicResource(resourceUrl = "", name = ""),
	variants: List<MarvelResource> = listOf(),
	collections: List<MarvelCollection<*>> = listOf(),
	collectedIssues: List<MarvelCollection<*>> = listOf(),
	dates: List<MarvelDate> = listOf(),
	prices: List<MarvelPrice> = listOf(),
	thumbnail: MarvelImage = MarvelImage(path = "", extension = ""),
	images: List<MarvelImage> = listOf(),
	creators: MarvelResources<CreatorResource> = MarvelResources(),
	characters: MarvelResources<HeroResource> = MarvelResources(),
	stories: MarvelResources<StoryResource> = MarvelResources(),
	events: MarvelResources<EventResource> = MarvelResources()
): ComicEntity = ComicEntity(
	id = id,
	digitalId = digitalId,
	title = title,
	issueNumber = issueNumber,
	variantDescription = variantDescription,
	description = description,
	modified = modified,
	isbn = isbn,
	upc = upc,
	diamondCode = diamondCode,
	ean = ean,
	issn = issn,
	format = format,
	pageCount = pageCount,
	textObjects = textObjects,
	resourceURI = resourceURI,
	urls = urls,
	series = series,
	variants = variants,
	collections = collections,
	collectedIssues = collectedIssues,
	dates = dates,
	prices = prices,
	thumbnail = thumbnail,
	images = images,
	creators = creators,
	characters = characters,
	stories = stories,
	events = events
)

val comicsEntity = listOf(
	comicEntity(),
	comicEntity(),
	comicEntity()
)

fun comic(
	id: String = "",
	digitalId: Int = - 1,
	title: String = "",
	issueNumber: Double = - 1.0,
	variantDescription: String = "",
	description: String = "",
	modified: String = "",
	isbn: String = "",
	upc: String = "",
	diamondCode: String = "",
	ean: String = "",
	issn: String = "",
	format: String = "",
	pageCount: Int = - 1,
	textObjects: List<TextObject> = listOf(),
	resourceURI: String = "",
	urls: List<MarvelUrl> = listOf(),
	series: ComicResource = ComicResource(resourceUrl = "", name = ""),
	variants: List<MarvelResource> = listOf(),
	collections: List<MarvelCollection<*>> = listOf(),
	collectedIssues: List<MarvelCollection<*>> = listOf(),
	dates: List<MarvelDate> = listOf(),
	prices: List<MarvelPrice> = listOf(),
	thumbnail: MarvelImage = MarvelImage(path = "", extension = ""),
	images: List<MarvelImage> = listOf(),
	creators: MarvelResources<CreatorResource> = MarvelResources(),
	characters: MarvelResources<HeroResource> = MarvelResources(),
	stories: MarvelResources<StoryResource> = MarvelResources(),
	events: MarvelResources<EventResource> = MarvelResources()
): Comic = Comic(
	id = id,
	digitalId = digitalId,
	title = title,
	issueNumber = issueNumber,
	variantDescription = variantDescription,
	description = description,
	modified = modified,
	isbn = isbn,
	upc = upc,
	diamondCode = diamondCode,
	ean = ean,
	issn = issn,
	format = format,
	pageCount = pageCount,
	textObjects = textObjects,
	resourceURI = resourceURI,
	urls = urls,
	series = series,
	variants = variants,
	collections = collections,
	collectedIssues = collectedIssues,
	dates = dates,
	prices = prices,
	thumbnail = thumbnail,
	images = images,
	creators = creators,
	characters = characters,
	stories = stories,
	events = events
)

val comics = listOf(comic(), comic(), comic())