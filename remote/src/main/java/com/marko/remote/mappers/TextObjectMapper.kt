package com.marko.remote.mappers

import com.karumi.marvelapiclient.model.TextObject

fun TextObject.toEntity(): com.marko.domain.entities.TextObject =
	com.marko.domain.entities.TextObject(
		type = type,
		language = language,
		text = text
	)

fun List<TextObject>.toEntity(): List<com.marko.domain.entities.TextObject> = map { it.toEntity() }