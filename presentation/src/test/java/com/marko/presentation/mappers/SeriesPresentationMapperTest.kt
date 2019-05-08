package com.marko.presentation.mappers

import com.marko.domain.entities.SeriesEntity
import com.marko.presentation.entities.Series
import com.marko.presentation.sampledata.seriesEntities
import com.marko.presentation.sampledata.seriesEntity
import org.junit.jupiter.api.Test

internal class SeriesPresentationMapperTest {

	@Test
	fun `test SeriesEntity to Series mapping`() {
		val seriesEntity = seriesEntity()

		val series = seriesEntity.toPresentation()

		seriesEntity shouldEqual series
	}

	@Test
	fun `test SeriesEntity list to Series list mapping`() {
		val seriesEntity = seriesEntities

		val series = seriesEntity.toPresentation()

		seriesEntity shouldEqual series
	}

	private infix fun List<SeriesEntity>.shouldEqual(seriesDatas: List<Series>) {
		assert(size == seriesDatas.size)
		repeat(size) { this[it] shouldEqual seriesDatas[it] }
	}

	private infix fun SeriesEntity.shouldEqual(series: Series) {
		assert(id == series.id)
		assert(title == series.title)
		assert(description == series.description)
		assert(resourceURI == series.resourceURI)
		assert(urls == series.urls)
		assert(startYear == series.startYear)
		assert(endYear == series.endYear)
		assert(rating == series.rating)
		assert(modified == series.modified)
		assert(thumbnail == series.thumbnail)
		assert(comics == series.comics)
		assert(stories == series.stories)
		assert(events == series.events)
		assert(characters == series.characters)
		assert(creators == series.creators)
		assert(next == series.next)
		assert(previous == series.previous)
	}
}