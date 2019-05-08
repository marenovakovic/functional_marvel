package com.marko.remote.mappers

import com.karumi.marvelapiclient.model.MarvelUrl

fun MarvelUrl.toUrl(): com.marko.domain.entities.MarvelUrl = com.marko.domain.entities.MarvelUrl(
	type = type,
	url = url
)

fun List<MarvelUrl>.toUrl(): List<com.marko.domain.entities.MarvelUrl> = map { it.toUrl() }