package com.marko.functional_marvel.mappers

import com.karumi.marvelapiclient.model.CharacterDto
import com.karumi.marvelapiclient.model.CharactersDto
import com.marko.functional_marvel.entities.Hero
import com.marko.functional_marvel.entities.Heroes

/**
 * Mapping [CharacterDto] to [Hero]
 *
 * @receiver [CharacterDto] that is being mapped to [Hero]
 *
 * @return [Hero] mapped from [CharacterDto]
 */
fun CharacterDto.toHero(): Hero = Hero(
	id = id,
	name = name,
	description = description,
	modified = modified,
	resourceUri = resourceUri,
	urls = urls.toHeroUrl(),
	thumbnail = thumbnail,
	comics = comics.toResource(),
	stories = stories.toResource(),
	events = events.toResource(),
	series = series.toResource()
)

/**
 * [CharactersDto] to [Heroes] using [toHero]
 *
 * @receiver [CharactersDto] that is being mapped to [Heroes]
 *
 * @return [Heroes] mapped from [CharactersDto]
 */
fun CharactersDto.toHeroes(): Heroes = characters.map { it.toHero() }

/**
 * Mapping [CharacterDto] [List] to [Heroes] using [toHero]
 *
 * @receiver [CharacterDto] [List] that is being mapped to [Heroes]
 *
 * @return [Hero] [List] mapped from [CharacterDto] [List]
 */
fun List<CharacterDto>.toHeroes(): Heroes = map { it.toHero() }