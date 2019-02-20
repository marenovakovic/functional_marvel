package com.marko.cache.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
	tableName = "favorite_heroes",
	foreignKeys = [ForeignKey(
		entity = HeroCache::class,
		childColumns = ["id"],
		parentColumns = ["id"],
		onDelete = CASCADE,
		onUpdate = CASCADE
	)]
)
data class FavoriteHero(
	@PrimaryKey val id: String
)