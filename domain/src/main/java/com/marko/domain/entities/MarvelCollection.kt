package com.marko.domain.entities

data class MarvelCollection<T>(
	val offset: Int,
	val limit: Int,
	val total: Int,
	val count: Int,
	val results: List<T>
)