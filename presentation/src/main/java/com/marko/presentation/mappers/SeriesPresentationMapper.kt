package com.marko.presentation.mappers

import com.marko.domain.entities.SeriesEntity
import com.marko.presentation.entities.Series

fun SeriesEntity.toPresentation(): Series = Series(
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

fun List<SeriesEntity>.toPresentation(): List<Series> = map { it.toPresentation() }