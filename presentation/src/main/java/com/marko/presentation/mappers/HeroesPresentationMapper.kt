package com.marko.presentation.mappers

import com.marko.domain.entities.HeroEntity
import com.marko.domain.entities.HeroesEntity
import com.marko.presentation.entities.Hero
import com.marko.presentation.entities.Heroes

fun HeroEntity.toPresentation(): Hero = Hero(
	id = id,
	name = name,
	description = description,
	modified = modified,
	resourceUri = resourceUri,
	isFavorite = isFavorite,
	urls = urls,
	thumbnail = thumbnail,
	comics = comics,
	stories = stories,
	events = events,
	series = series
)

fun HeroesEntity.toPresentation(): Heroes = map { it.toPresentation() }

fun Hero.toEntity(): HeroEntity = HeroEntity(
	id = id,
	name = name,
	description = description,
	modified = modified,
	resourceUri = resourceUri,
	isFavorite = isFavorite,
	urls = urls,
	thumbnail = thumbnail,
	comics = comics,
	stories = stories,
	events = events,
	series = series
)

fun Heroes.toEntity(): HeroesEntity = map { it.toEntity() }