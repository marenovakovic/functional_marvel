package com.marko.remote.mappers

import com.karumi.marvelapiclient.model.SeriesCollectionDto
import com.karumi.marvelapiclient.model.SeriesDto
import com.marko.data.entities.SeriesData
import com.marko.domain.entities.MarvelImage
import com.marko.domain.entities.MarvelResources
import com.marko.domain.entities.SeriesResource

fun SeriesDto.toData(): SeriesData = SeriesData(
	id = id ?: "",
	title = title ?: "",
	description = description ?: "",
	resourceURI = resourceURI ?: "",
	urls = urls.toUrl(),
	startYear = startYear,
	endYear = endYear,
	rating = rating ?: "",
	modified = modified ?: "",
	thumbnail = MarvelImage(path = thumbnail.path, extension = thumbnail.extension),
	comics = MarvelResources(),
	stories = MarvelResources(),
	events = MarvelResources(),
	characters = MarvelResources(),
	creators = MarvelResources(),
	next = next?.let { SeriesResource(resourceUrl = it.resourceUri, name = it.name) }
		?: SeriesResource(resourceUrl = "", name = ""),
	previous = previous?.let { SeriesResource(resourceUrl = it.resourceUri, name = it.name) }
		?: SeriesResource(resourceUrl = "", name = "")
)

fun SeriesCollectionDto.toData(): List<SeriesData> = series.map { it.toData() }