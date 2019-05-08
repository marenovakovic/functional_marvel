package com.marko.functional_marvel.entities

import com.marko.presentation.entities.MarvelItem

data class HeroDetailsParentEntity(
	val title: String,
	val items: List<MarvelItem>
)