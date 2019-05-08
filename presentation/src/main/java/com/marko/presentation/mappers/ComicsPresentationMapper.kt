package com.marko.presentation.mappers

import com.marko.domain.entities.ComicEntity
import com.marko.domain.entities.ComicsEntity
import com.marko.presentation.entities.Comic
import com.marko.presentation.entities.Comics

fun ComicEntity.toPresentation(): Comic = Comic(
	id = id,
	digitalId = digitalId,
	title = title,
	issueNumber = issueNumber,
	variantDescription = variantDescription,
	description = description,
	modified = modified,
	isbn = isbn,
	upc = upc,
	diamondCode = diamondCode,
	ean = ean,
	issn = issn,
	format = format,
	pageCount = pageCount,
	textObjects = textObjects,
	resourceURI = resourceURI,
	urls = urls,
	series = series,
	variants = variants,
	collections = collections,
	collectedIssues = collectedIssues,
	dates = dates,
	prices = prices,
	thumbnail = thumbnail,
	images = images,
	creators = creators,
	characters = characters,
	stories = stories,
	events = events
)

fun ComicsEntity.toPresentation(): Comics = map { it.toPresentation() }

fun Comic.toEntity(): ComicEntity = ComicEntity(
	id = id,
	digitalId = digitalId,
	title = title,
	issueNumber = issueNumber,
	variantDescription = variantDescription,
	description = description,
	modified = modified,
	isbn = isbn,
	upc = upc,
	diamondCode = diamondCode,
	ean = ean,
	issn = issn,
	format = format,
	pageCount = pageCount,
	textObjects = textObjects,
	resourceURI = resourceURI,
	urls = urls,
	series = series,
	variants = variants,
	collections = collections,
	collectedIssues = collectedIssues,
	dates = dates,
	prices = prices,
	thumbnail = thumbnail,
	images = images,
	creators = creators,
	characters = characters,
	stories = stories,
	events = events
)

fun Comics.toEntity(): ComicsEntity = map { it.toEntity() }