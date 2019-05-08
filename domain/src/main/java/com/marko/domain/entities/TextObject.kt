package com.marko.domain.entities

/**
 * Domain layer text object representation
 */
data class TextObject(
	val type: String,
	val language: String,
	val text: String
)