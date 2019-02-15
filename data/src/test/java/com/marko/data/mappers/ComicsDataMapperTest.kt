package com.marko.data.mappers

import com.marko.data.entities.ComicData
import com.marko.data.entities.ComicsData
import com.marko.data.sampledata.comicData
import com.marko.data.sampledata.comicEntity
import com.marko.data.sampledata.comicsData
import com.marko.data.sampledata.comicsEntity
import com.marko.domain.entities.ComicEntity
import com.marko.domain.entities.ComicsEntity
import org.junit.jupiter.api.Test

internal class ComicsDataMapperTest {

	@Test
	fun `test ComicEntity to ComicData mapping`() {
		val comicEntity = comicEntity()

		val comicData = comicEntity.toData()

		comicEntity shouldEquals comicData
	}

	@Test
	fun `test ComicsEntity to ComicsData mapping`() {
		val comicsEntity = comicsEntity

		val comicsData = comicsEntity.toData()

		comicsEntity shouldEquals comicsData
	}

	@Test
	fun `test ComicData to ComicEntity mapping`() {
		val comicData = comicData()

		val comicEntity = comicData.toEntity()

		comicEntity shouldEquals comicData
	}

	@Test
	fun `test ComicsData to ComicsEntity mapping`() {
		val comicsData = comicsData

		val comicsEntity = comicsData.toEntity()

		comicsEntity shouldEquals comicsData
	}

	private infix fun ComicsEntity.shouldEquals(comicData: ComicsData) {
		assert(this.size == comicsData.size)

		repeat(this.size) { this[it] shouldEquals comicsData[it] }
	}

	private infix fun ComicEntity.shouldEquals(comicData: ComicData) {
		assert(id == comicData.id)
		assert(digitalId == comicData.digitalId)
		assert(title == comicData.title)
		assert(issueNumber == comicData.issueNumber)
		assert(variantDescription == comicData.variantDescription)
		assert(description == comicData.description)
		assert(modified == comicData.modified)
		assert(isbn == comicData.isbn)
		assert(upc == comicData.upc)
		assert(diamondCode == comicData.diamondCode)
		assert(ean == comicData.ean)
		assert(issn == comicData.issn)
		assert(format == comicData.format)
		assert(pageCount == comicData.pageCount)
		assert(textObjects == comicData.textObjects)
		assert(resourceURI == comicData.resourceURI)
		assert(urls == comicData.urls)
		assert(series.resourceUrl == comicData.series.resourceUrl)
		assert(series.name == comicData.series.name)
		assert(variants == comicData.variants)
		assert(collections == comicData.collections)
		assert(collectedIssues == comicData.collectedIssues)
		assert(dates == comicData.dates)
		assert(prices == comicData.prices)
		assert(thumbnail == comicData.thumbnail)
		assert(images == comicData.images)
		assert(creators == comicData.creators)
		assert(characters == comicData.characters)
		assert(stories == comicData.stories)
		assert(events == comicData.events)
	}
}