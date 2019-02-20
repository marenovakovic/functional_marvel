package com.marko.data.mappers

import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.domain.entities.HeroEntity
import com.marko.domain.entities.HeroesEntity

fun HeroEntity.toData(): HeroData = HeroData(
	id = id,
	name = name,
	isFavorite = isFavorite,
	description = description,
	modified = modified,
	resourceUri = resourceUri,
	urls = urls,
	thumbnail = thumbnail,
	comics = comics,
	stories = stories,
	events = events,
	series = series
)

fun HeroesEntity.toData(): HeroesData = map { it.toData() }

fun HeroData.toEntity(): HeroEntity = HeroEntity(
	id = id,
	name = name,
	isFavorite = isFavorite,
	description = description,
	modified = modified,
	resourceUri = resourceUri,
	urls = urls,
	thumbnail = thumbnail,
	comics = comics,
	stories = stories,
	events = events,
	series = series
)

fun HeroesData.toEntity(): HeroesEntity = map { it.toEntity() }