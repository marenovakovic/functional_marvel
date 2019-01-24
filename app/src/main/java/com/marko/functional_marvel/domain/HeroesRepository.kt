package com.marko.functional_marvel.domain

import arrow.Kind
import com.marko.functional_marvel.entities.Hero
import com.marko.functional_marvel.entities.Heroes

/**
 * Data layer access point for [Heroes]
 *
 * [F] Higher-kind type
 */
interface HeroesRepository<F> {

	/**
	 * Save [Hero]
	 *
	 * @return [Kind]
	 */
	fun saveHero(hero: Hero): Kind<F, Unit>

	/**
	 * Get [Heroes]
	 *
	 * @return [Kind]
	 */
	fun getHeroes(): Kind<F, Heroes>
}