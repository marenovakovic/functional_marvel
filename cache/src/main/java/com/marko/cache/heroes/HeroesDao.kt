package com.marko.cache.heroes

import androidx.room.*
import com.marko.cache.entities.FavoriteHero
import com.marko.cache.entities.HeroCache
import com.marko.domain.entities.HeroId

/**
 * [Dao] for [HeroCache] operations
 */
@Dao
interface HeroesDao {

	/**
	 * Query single [HeroCache]
	 *
	 * @param heroId [HeroId] of [HeroCache] that query is for
	 *
	 * @return [HeroCache] with matching [HeroId]
	 */
	@Query("SELECT * FROM heroes WHERE id = :heroId")
	fun queryHero(heroId: HeroId): HeroCache

	/**
	 * Query all [HeroCache]
	 *
	 * @return [HeroCache] [List]
	 */
	@Query("SELECT * FROM heroes")
	fun queryAllHeroes(): List<HeroCache>

	/**
	 * Query [HeroCache] matching ids passed
	 *
	 * @param ids [HeroId] [List] ids of heroes that are queried for
	 *
	 * @return [HeroCache] [List]
	 */
	@Query("SELECT * FROM heroes WHERE id IN(:ids)")
	fun queryHeroes(ids: List<HeroId>): List<HeroCache>

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
	 * Query all [FavoriteHero] from database
	 *
	 * @return [FavoriteHero] [List]
	 */
	@Query("SELECT * FROM favorite_heroes")
	fun queryFavorites(): List<FavoriteHero>

	/**
	 * Insert [FavoriteHero] to favorites
	 *
	 * @param favoriteHero [FavoriteHero] that should be inserted into favorites
	 */
	@Insert
	fun setFavorite(favoriteHero: FavoriteHero)

	/**
	 * Delete hero from favorites
	 *
	 * @param favoriteHero [FavoriteHero] to be removed from favorites
	 */
	@Delete
	fun removeFavorite(favoriteHero: FavoriteHero)
}