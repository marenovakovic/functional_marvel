package com.marko.functional_marvel.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marko.functional_marvel.entities.HeroCache

@Database(entities = [HeroCache::class], version = 1)
abstract class MarvelDatabase : RoomDatabase() {

	abstract fun heroesDao(): HeroesDao

	companion object {
		private var instance: MarvelDatabase? = null

		@Synchronized
		fun getInstance(context: Context): MarvelDatabase =
			instance ?: Room.databaseBuilder(
				context,
				MarvelDatabase::class.java,
				"marvel.db"
			).build().also {
				instance = it
			}
	}
}