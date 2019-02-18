package com.marko.data.mappers

import com.marko.data.entities.SeriesData
import com.marko.domain.entities.SeriesEntity

fun SeriesEntity.toData(): SeriesData = SeriesData(
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

fun List<SeriesEntity>.toData(): List<SeriesData> = map { it.toData() }

fun SeriesData.toEntity(): SeriesEntity = SeriesEntity(
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

fun List<SeriesData>.toEntity(): List<SeriesEntity> = map { it.toEntity() }