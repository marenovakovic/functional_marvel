package com.marko.remote.mappers

import com.karumi.marvelapiclient.model.CharacterDto
import com.marko.data.entities.HeroData
import com.marko.domain.entities.MarvelImage
import com.marko.domain.entities.MarvelResources

fun CharacterDto.toData(): HeroData = HeroData(
	id = id,
	name = name,
	description = description,
	modified = modified,
	resourceUri = resourceUri,
	urls = urls.toUrl(),
	thumbnail = MarvelImage(),
	comics = MarvelResources(),
	stories = MarvelResources(),
	events = MarvelResources(),
	series = MarvelResources()
)