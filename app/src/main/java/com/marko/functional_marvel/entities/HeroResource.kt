package com.marko.functional_marvel.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class HeroResource<T>(
	val available: Int = - 1,
	val returned: Int = - 1,
	val collectionUri: String = "",
	val items: @RawValue List<T> = emptyList()
) : Parcelable