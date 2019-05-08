package com.marko.presentation.mappers

import com.marko.domain.entities.ComicEntity
import com.marko.domain.entities.ComicsEntity
import com.marko.presentation.entities.Comic
import com.marko.presentation.entities.Comics
import com.marko.presentation.sampledata.comic
import com.marko.presentation.sampledata.comicEntity
import com.marko.presentation.sampledata.comics
import com.marko.presentation.sampledata.comicsEntity
import org.junit.jupiter.api.Test

internal class ComicsPresentationMapperTest {

	@Test
	fun `test ComicEntity to Comic mapping`() {
		val comicEntity = comicEntity()

		val comic = comicEntity.toPresentation()

		comicEntity shouldEquals comic
	}

	@Test
	fun `test ComicsEntity to Comics mapping`() {
		val comicsEntity = comicsEntity

		val comics = comicsEntity.toPresentation()

		comicsEntity shouldEquals comics
	}

	@Test
	fun `test Comic to ComicEntity mapping`() {
		val comic = comic()

		val comicEntity = comic.toEntity()

		comicEntity shouldEquals comic
	}

	@Test
	fun `test Comics to ComicsEntity mapping`() {
		val comics = comics

		val comicsEntity = comics.toEntity()

		comicsEntity shouldEquals comics
	}

	private infix fun ComicsEntity.shouldEquals(comics: Comics) {
		kotlin.assert(this.size == comics.size)

		kotlin.repeat(this.size) { this[it] shouldEquals comics[it] }
	}

	private infix fun ComicEntity.shouldEquals(comic: Comic) {
		assert(id == comic.id)
		assert(digitalId == comic.digitalId)
		assert(title == comic.title)
		assert(issueNumber == comic.issueNumber)
		assert(variantDescription == comic.variantDescription)
		assert(description == comic.description)
		assert(modified == comic.modified)
		assert(isbn == comic.isbn)
		assert(upc == comic.upc)
		assert(diamondCode == comic.diamondCode)
		assert(ean == comic.ean)
		assert(issn == comic.issn)
		assert(format == comic.format)
		assert(pageCount == comic.pageCount)
		assert(textObjects == comic.textObjects)
		assert(resourceURI == comic.resourceURI)
		assert(urls == comic.urls)
		assert(series.resourceUrl == comic.series.resourceUrl)
		assert(series.name == comic.series.name)
		assert(variants == comic.variants)
		assert(collections == comic.collections)
		assert(collectedIssues == comic.collectedIssues)
		assert(dates == comic.dates)
		assert(prices == comic.prices)
		assert(thumbnail == comic.thumbnail)
		assert(images == comic.images)
		assert(creators == comic.creators)
		assert(characters == comic.characters)
		assert(stories == comic.stories)
		assert(events == comic.events)
	}
}