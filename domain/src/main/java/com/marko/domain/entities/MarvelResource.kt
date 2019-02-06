package com.marko.domain.entities

sealed class MarvelResource(
	val resourceUrl: String,
	val name: String
)

class ComicResource(
	resourceUrl: String,
	name: String
) : MarvelResource(resourceUrl = resourceUrl, name = name)

class StoryResource(
	resourceUrl: String,
	name: String
) : MarvelResource(resourceUrl = resourceUrl, name = name)

class EventResource(
	resourceUrl: String,
	name: String
) : MarvelResource(resourceUrl = resourceUrl, name = name)

class SeriesResource(
	resourceUrl: String,
	name: String
) : MarvelResource(resourceUrl = resourceUrl, name = name)