package com.marko.functional_marvel.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeroUrl(
	val type: String,
	val url: String
) : Parcelable