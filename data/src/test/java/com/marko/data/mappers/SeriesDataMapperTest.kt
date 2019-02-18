package com.marko.data.mappers

import com.marko.data.entities.SeriesData
import com.marko.data.sampledata.seriesData
import com.marko.data.sampledata.seriesDatas
import com.marko.data.sampledata.seriesEntities
import com.marko.data.sampledata.seriesEntity
import com.marko.domain.entities.SeriesEntity
import org.junit.jupiter.api.Test

internal class SeriesDataMapperTest {

	@Test
	fun `test SeriesEntity to SeriesData mapping`() {
		val seriesEntity = seriesEntity()

		val seriesData = seriesEntity.toData()

		seriesEntity shouldEqual seriesData
	}

	@Test
	fun `test SeriesEntity list to SeriesData list mapping`() {
		val seriesEntities = seriesEntities

		val seriesDatas = seriesEntities.toData()

		seriesEntities shouldEqual seriesDatas
	}

	@Test
	fun `test SeriesData to SeriesEntity mapping`() {
		val seriesData = seriesData()

		val seriesEntity = seriesData.toEntity()

		seriesEntity shouldEqual seriesData
	}

	@Test
	fun `test SeriesData list to SeriesEntity list mapping`() {
		val seriesDatas = seriesDatas

		val seriesEntities = seriesDatas.toEntity()

		seriesEntities shouldEqual seriesDatas
	}

	private infix fun List<SeriesEntity>.shouldEqual(seriesDatas: List<SeriesData>) {
		assert(size == seriesDatas.size)
		repeat(size) { this[it] shouldEqual seriesDatas[it] }
	}

	private infix fun SeriesEntity.shouldEqual(seriesData: SeriesData) {
		assert(id == seriesData.id)
		assert(title == seriesData.title)
		assert(description == seriesData.description)
		assert(resourceURI == seriesData.resourceURI)
		assert(urls == seriesData.urls)
		assert(startYear == seriesData.startYear)
		assert(endYear == seriesData.endYear)
		assert(rating == seriesData.rating)
		assert(modified == seriesData.modified)
		assert(thumbnail == seriesData.thumbnail)
		assert(comics == seriesData.comics)
		assert(stories == seriesData.stories)
		assert(events == seriesData.events)
		assert(characters == seriesData.characters)
		assert(creators == seriesData.creators)
		assert(next == seriesData.next)
		assert(previous == seriesData.previous)
	}
}