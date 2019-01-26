package com.marko.functional_marvel.sampledata

import com.karumi.marvelapiclient.model.*
import com.marko.functional_marvel.entities.Comic
import com.marko.functional_marvel.entities.Comics
import com.marko.functional_marvel.entities.HeroResource
import com.marko.functional_marvel.entities.HeroUrl

fun comic(
	id: String = "id",
	digitalId: Int = 1,
	title: String = "title",
	issueNumber: Double = 1.0,
	variantDescription: String = "variantDescription",
	description: String = "description",
	modified: String = "modified",
	isbn: String = "isbn",
	upc: String = "upc",
	diamondCode: String = "diamondCode",
	ean: String = "ean",
	issn: String = "issn",
	format: String = "format",
	pageCount: Int = 1,
	textObjects: List<TextObject> = listOf(),
	resourceURI: String = "resourceURI",
	urls: List<HeroUrl> = listOf(),
	series: ComicResourceDto = ComicResourceDto(),
	variants: List<HeroResource<*>> = listOf(),
	collections: List<MarvelCollection<*>> = listOf(),
	collectedIssues: List<MarvelCollection<*>> = listOf(),
	dates: List<MarvelDate> = listOf(),
	prices: List<MarvelPrice> = listOf(),
	thumbnail: MarvelImage = MarvelImage(),
	images: List<MarvelImage> = listOf(),
	creators: HeroResource<CreatorResourceDto> = HeroResource(),
	characters: HeroResource<CharacterResourceDto> = HeroResource(),
	stories: HeroResource<StoryResourceDto> = HeroResource(),
	events: HeroResource<EventResourceDto> = HeroResource()
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

val sampleComics: Comics = listOf(comic(), comic(), comic())