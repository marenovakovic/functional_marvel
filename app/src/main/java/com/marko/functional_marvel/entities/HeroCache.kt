package com.marko.functional_marvel.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.karumi.marvelapiclient.model.ComicResourceDto
import com.karumi.marvelapiclient.model.EventResourceDto
import com.karumi.marvelapiclient.model.SerieResourceDto
import com.karumi.marvelapiclient.model.StoryResourceDto

/**
 * Hero cache representation with Room
 */
@Entity(tableName = "heroes")
data class HeroCache(
	@PrimaryKey var id: String = "",
	var name: String = "",
	var description: String = "",
	@Ignore var modified: String = "",
	@Ignore var resourceUri: String = "",
	@Ignore var urls: List<HeroUrl> = listOf(),
	@Ignore var thumbnail: HeroImage = HeroImage(),
	@Ignore var comics: HeroResource<ComicResourceDto> = HeroResource(),
	@Ignore var stories: HeroResource<StoryResourceDto> = HeroResource(),
	@Ignore var events: HeroResource<EventResourceDto> = HeroResource(),
	@Ignore var series: HeroResource<SerieResourceDto> = HeroResource()
)