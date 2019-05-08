package com.marko.cache.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.marko.domain.entities.*

/**
 * Hero cache representation with Room
 */
@Entity(tableName = "heroes")
data class HeroCache(
	@PrimaryKey var id: String = "",
	var name: String = "",
	@Ignore var isFavorite: Boolean = false,
	var description: String = "",
	var imageUrl: String = "",
	@Ignore var modified: String = "",
	@Ignore var resourceUri: String = "",
	@Ignore var urls: List<MarvelUrl> = listOf(),
	@Ignore var thumbnail: MarvelImage = MarvelImage(path = "", extension = ""),
	@Ignore var comics: MarvelResources<ComicResource> = MarvelResources(),
	@Ignore var stories: MarvelResources<StoryResource> = MarvelResources(),
	@Ignore var events: MarvelResources<EventResource> = MarvelResources(),
	@Ignore var series: MarvelResources<SeriesResource> = MarvelResources()
)