package com.marko.domain.entities

data class MarvelResources<T>(
	val available: Int,
	val returned: Int,
	val collectionUri: String,
	val items: List<T>
) {
	constructor() : this(available = - 1, returned = - 1, collectionUri = "", items = emptyList())
}