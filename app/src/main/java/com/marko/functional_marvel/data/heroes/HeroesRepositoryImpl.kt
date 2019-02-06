package com.marko.functional_marvel.data.heroes

import arrow.Kind
import arrow.effects.typeclasses.MonadDefer
import com.marko.functional_marvel.domain.heroes.HeroesRepository
import com.marko.functional_marvel.entities.Hero
import com.marko.functional_marvel.entities.Heroes
import com.marko.functional_marvel.exceptions.ContentNotAvailable
import com.marko.functional_marvel.exceptions.SaveException
import com.marko.functional_marvel.injection.DI
import javax.inject.Inject
import javax.inject.Named

/**
 * [HeroesRepository] implementation. Implements [MonadDefer] by delegating
 *
 * [F] Higher-kind type
 *
 * @param monadDefer [MonadDefer] for lazy evaluation
 *
 * @param heroesRemoteSource [HeroesDataSource] API access point
 *
 * @param heroesCacheSource [HeroesDataSource] database access point
 */
class HeroesRepositoryImpl<F> @Inject constructor(
	private val monadDefer: MonadDefer<F>,
	@Named(DI.HEROES_REMOTE_SOURCE) private val heroesRemoteSource: HeroesDataSource<F>,
	@Named(DI.HEROES_CACHE_SOURCE) private val heroesCacheSource: HeroesDataSource<F>
) : HeroesRepository<F>, MonadDefer<F> by monadDefer {

	override fun getHeroes(): Kind<F, Heroes> = defer {
		heroesRemoteSource.getHeroes()
			.handleErrorWith { raiseError(ContentNotAvailable) }
	}

	override fun saveHero(hero: Hero): Kind<F, Unit> =
		defer { heroesCacheSource.saveHero(hero) }
			.handleErrorWith { raiseError(SaveException) }

	override fun <A, B> Kind<F, A>.ap(ff: Kind<F, (A) -> B>): Kind<F, B> =
		monadDefer.run { this@ap.ap(ff) }

	override fun <A, B> Kind<F, A>.map(f: (A) -> B): Kind<F, B> =
		monadDefer.run { this@map.map(f) }
}