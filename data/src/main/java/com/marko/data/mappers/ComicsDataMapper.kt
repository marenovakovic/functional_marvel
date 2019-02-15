package com.marko.data.mappers

import com.marko.data.entities.ComicData
import com.marko.data.entities.ComicsData
import com.marko.domain.entities.ComicEntity
import com.marko.domain.entities.ComicsEntity

fun ComicEntity.toData() = ComicData(
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

fun ComicsEntity.toData(): ComicsData = map { it.toData() }

fun ComicData.toEntity(): ComicEntity = ComicEntity(
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

fun ComicsData.toEntity(): ComicsEntity = map { it.toEntity() }