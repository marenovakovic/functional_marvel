package com.marko.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes_favorite")
data class HeroFavorite(
	@PrimaryKey var id: String = "",
	var name: String = "",
	var description: String = ""
)