package com.marko.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marko.cache.entities.FavoriteHero
import com.marko.cache.entities.HeroCache
import com.marko.cache.heroes.HeroesDao

@Database(entities = [HeroCache::class, FavoriteHero::class], version = 2)
abstract class MarvelDatabase : RoomDatabase() {

	abstract fun heroesDao(): HeroesDao

	companion object {
		private var instance: MarvelDatabase? = null

		@Synchronized
		fun getInstance(context: Context): MarvelDatabase =
			instance ?: Room.databaseBuilder(context, MarvelDatabase::class.java, "marvel.db")
				.build().also { instance = it }
	}
}