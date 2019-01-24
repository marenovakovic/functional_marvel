package com.marko.functional_marvel.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marko.functional_marvel.entities.HeroCache
import com.marko.functional_marvel.entities.HeroId

/**
 * [Dao] for [HeroCache] operations
 */
@Dao
interface HeroesDao {

	/**
	 * Insert single [HeroCache] into database
	 *
	 * @param hero [HeroCache] that should be inserted into database
	 */
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun saveHero(hero: HeroCache)

	/**
	 * Insert [HeroCache] [List] into database
	 *
	 * @param heroes [HeroCache] [List] that should be inserted into database
	 */
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun saveHeroes(heroes: List<HeroCache>)

	/**
	 * Query single [HeroCache] from database
	 *
	 * @param heroId [HeroId] of [HeroCache] that query is for
	 *
	 * @return [HeroCache] with matching [HeroId]
	 */
	@Query("SELECT * FROM heroes WHERE id = :heroId")
	fun queryHero(heroId: HeroId): HeroCache

	/**
	 * Query all [HeroCache] from database
	 *
	 * @return [HeroCache] [List]
	 */
	@Query("SELECT * FROM heroes")
	fun queryAllHeroes(): List<HeroCache>
}