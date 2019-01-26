package com.marko.functional_marvel.mappers

import com.karumi.marvelapiclient.model.ComicDto
import com.karumi.marvelapiclient.model.ComicsDto
import com.marko.functional_marvel.entities.Comic
import com.marko.functional_marvel.entities.Comics

/**
 * Mapping [ComicDto] to [Comic]
 *
 * @receiver [ComicDto] that is being mapped to [Comic]
 *
 * @return [Comic] mapped from [ComicDto]
 */
fun ComicDto.toComic(): Comic = Comic(
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
	urls = urls.toHeroUrl(),
	series = series,
	variants = listOf(),
	collections = collections,
	collectedIssues = collectedIssues,
	dates = dates,
	prices = prices,
	thumbnail = thumbnail,
	images = images,
	creators = creators.toResource(),
	characters = characters.toResource(),
	stories = stories.toResource(),
	events = events.toResource()
)

/**
 * Mapping [ComicsDto] to [Comics]
 *
 * @receiver [ComicsDto] that is being mapped to [Comics]
 *
 * @return [Comics] mapped from [ComicsDto]
 */
fun ComicsDto.toComic(): Comics = comics.map { it.toComic() }