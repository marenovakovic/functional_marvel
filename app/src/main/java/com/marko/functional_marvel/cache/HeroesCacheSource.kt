package com.marko.functional_marvel.cache

import arrow.Kind
import arrow.core.Try
import arrow.effects.typeclasses.Async
import com.marko.functional_marvel.data.heroes.HeroesDataSource
import com.marko.functional_marvel.entities.Hero
import com.marko.functional_marvel.entities.HeroId
import com.marko.functional_marvel.entities.Heroes
import com.marko.functional_marvel.mappers.toCache
import com.marko.functional_marvel.mappers.toHero
import com.marko.functional_marvel.mappers.toHeroes
import javax.inject.Inject

/**
 * [Heroes] database access point. Implements [Async] by delegating
 *
 * @param async [Async] for asynchronous operations
 *
 * @param heroesDao [HeroesDao] for database access
 *
 * [F] Higher-kind type
 */
class HeroesCacheSource<F> @Inject constructor(
	private val async: Async<F>,
	private val heroesDao: HeroesDao
) : HeroesDataSource<F>, Async<F> by async {

	override fun saveHero(hero: Hero): Kind<F, Unit> =
		async { callback ->
			val result = Try { heroesDao.saveHero(hero = hero.toCache()) }.toEither()
			callback(result)
		}

	override fun saveHeroes(heroes: Heroes): Kind<F, Unit> =
		async { callback ->
			val result = Try { heroesDao.saveHeroes(heroes = heroes.toCache()) }.toEither()
			callback(result)
		}

	override fun getHero(heroId: HeroId): Kind<F, Hero> =
		async { callback ->
			val hero = Try { heroesDao.queryHero(heroId = heroId) }.toEither().map { it.toHero() }
			callback(hero)
		}

	override fun getHeroes(): Kind<F, Heroes> =
		async { callback ->
			val heroes = Try { heroesDao.queryAllHeroes() }.toEither().map { it.toHeroes() }
			callback(heroes)
		}

	override fun <A, B> Kind<F, A>.ap(ff: Kind<F, (A) -> B>): Kind<F, B> =
		async.run { this@ap.ap(ff) }

	override fun <A, B> Kind<F, A>.map(f: (A) -> B): Kind<F, B> =
		async.run { this@map.map(f) }
}