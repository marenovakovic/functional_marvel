package com.marko.functional_marvel.remote.heroes

import arrow.Kind
import arrow.core.Left
import arrow.core.Try
import arrow.effects.typeclasses.Async
import com.karumi.marvelapiclient.CharacterApiClient
import com.marko.functional_marvel.data.heroes.HeroesDataSource
import com.marko.functional_marvel.entities.Hero
import com.marko.functional_marvel.entities.HeroId
import com.marko.functional_marvel.entities.Heroes
import com.marko.functional_marvel.exceptions.UnsupportedCaching
import com.marko.functional_marvel.extensions.safe
import com.marko.functional_marvel.mappers.toHero
import com.marko.functional_marvel.mappers.toHeroes
import javax.inject.Inject

/**
 * [Heroes] API access point. Implements [Async] by delegating
 *
 * @param async [Async] for asynchronous operations
 *
 * @param characterApi [CharacterApiClient] for API access
 *
 * [F] Higher-kind type
 */
class HeroesRemoteSource<F> @Inject constructor(
	private val async: Async<F>,
	private val characterApi: CharacterApiClient
) : HeroesDataSource<F>, Async<F> by async {

	override fun saveHero(hero: Hero): Kind<F, Unit> = async { callback ->
		val result = Left(UnsupportedCaching("HeroesRemoteSource doesn't support caching"))
		callback(result)
	}

	override fun saveHeroes(heroes: Heroes): Kind<F, Unit> = async { callback ->
		val result = Left(UnsupportedCaching("HeroesRemoteSource doesn't support caching"))
		callback(result)
	}

	override fun getHero(heroId: HeroId): Kind<F, Hero> =
		async { callback ->
			val hero = Try { characterApi.getCharacter(heroId) }
				.flatMap { it.safe }
				.map { it.toHero() }
				.toEither()

			callback(hero)
		}

	override fun getHeroes(): Kind<F, Heroes> = async { callback ->
		val heroes = Try { characterApi.getAll(0, 50) }
			.flatMap { it.safe }
			.map { it.toHeroes() }
			.toEither()

		callback(heroes)
	}

	override fun <A, B> Kind<F, A>.ap(ff: Kind<F, (A) -> B>): Kind<F, B> =
		async.run { this@ap.ap(ff) }

	override fun <A, B> Kind<F, A>.map(f: (A) -> B): Kind<F, B> =
		async.run { this@map.map(f) }
}