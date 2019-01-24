package com.marko.functional_marvel.entities

import android.os.Parcelable
import com.karumi.marvelapiclient.model.ComicResourceDto
import com.karumi.marvelapiclient.model.EventResourceDto
import com.karumi.marvelapiclient.model.SerieResourceDto
import com.karumi.marvelapiclient.model.StoryResourceDto
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

typealias HeroId = String

/**
 * Hero representation
 */
@Parcelize
data class Hero(
	val id: String,
	val name: String,
	val description: String,
	val modified: String,
	val resourceUri: String,
	val isFavorite: Boolean = false,
	val urls: List<HeroUrl>,
	val thumbnail: @RawValue HeroImage,
	val comics: HeroResource<ComicResourceDto>,
	val stories: HeroResource<StoryResourceDto>,
	val events: HeroResource<EventResourceDto>,
	val series: HeroResource<SerieResourceDto>
) : Parcelable