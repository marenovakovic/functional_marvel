package com.marko.functional_marvel.data.heroes

import arrow.Kind
import com.marko.functional_marvel.entities.Hero
import com.marko.functional_marvel.entities.HeroId
import com.marko.functional_marvel.entities.Heroes

/**
 * Data source describing common [Heroes] related operations
 *
 * [F] Higher-kind type
 */
interface HeroesDataSource<F> {

	/**
	 * Save [Hero]
	 *
	 * @return [Kind]
	 */
	fun saveHero(hero: Hero): Kind<F, Unit>

	/**
	 * Save [Heroes]
	 *
	 * @return [Kind]
	 */
	fun saveHeroes(heroes: Heroes): Kind<F, Unit>

	/**
	 * Get [Hero]
	 *
	 * @param heroId [HeroId] of [Hero] that should be fetched
	 *
	 * @return [Kind]
	 */
	fun getHero(heroId: HeroId): Kind<F, Hero>

	/**
	 * Get [Heroes]
	 *
	 * @return [Kind]
	 */
	fun getHeroes(): Kind<F, Heroes>
}