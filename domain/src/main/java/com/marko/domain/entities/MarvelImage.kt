package com.marko.domain.entities

data class MarvelImage(
	val path: String,
	val extension: String,
	val imageUrl: String = "$path/standard_medium.$extension"
)