package com.marko.remote.mappers

import com.karumi.marvelapiclient.model.ComicDto
import com.karumi.marvelapiclient.model.ComicsDto
import com.marko.data.entities.ComicData
import com.marko.data.entities.ComicsData
import com.marko.domain.entities.*

fun ComicDto.toData(): ComicData = ComicData(
	id = id,
	digitalId = digitalId,
	title = title,
	issueNumber = issueNumber,
	variantDescription = variantDescription,
	description = description ?: "",
	modified = modified,
	isbn = isbn,
	upc = upc,
	diamondCode = diamondCode,
	ean = ean,
	issn = issn,
	format = format,
	pageCount = pageCount,
	textObjects = textObjects.toEntity(),
	resourceURI = resourceURI,
	urls = urls.toUrl(),
	series = ComicResource(resourceUrl = series.resourceUri, name = series.name),
	variants = listOf(),
	collections = listOf(),
	collectedIssues = listOf(),
	dates = dates.map { MarvelDate(type = it.type, data = it.date) },
	prices = prices.map { MarvelPrice(type = it.type, price = it.price.toString()) },
	thumbnail = MarvelImage(path = thumbnail.path, extension = thumbnail.extension),
	images = images.map { MarvelImage(path = it.path, extension = it.extension) },
	creators = MarvelResources(),
	characters = MarvelResources(),
	stories = MarvelResources(),
	events = MarvelResources()
)

fun ComicsDto.toData(): ComicsData = comics.map { it.toData() }