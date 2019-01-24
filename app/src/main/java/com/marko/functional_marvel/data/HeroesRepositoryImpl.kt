package com.marko.functional_marvel.data

import arrow.Kind
import arrow.effects.typeclasses.MonadDefer
import com.marko.functional_marvel.domain.HeroesRepository
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
 * @param monadDefer [MonadDefer] for lazy evaluation
 *
 * @param heroesRemoteSource [HeroesDataSource] API access point
 *
 * @param heroesCacheSource [HeroesDataSource] database access point
 */
class HeroesRepositoryImpl<F> @Inject constructor(
	monadDefer: MonadDefer<F>,
	@Named(DI.HEROES_REMOTE_SOURCE) private val heroesRemoteSource: HeroesDataSource<F>,
	@Named(DI.HEROES_CACHE_SOURCE) private val heroesCacheSource: HeroesDataSource<F>
) : HeroesRepository<F>, MonadDefer<F> by monadDefer {

	override fun getHeroes(): Kind<F, Heroes> =
		defer {
			tupled(
				heroesRemoteSource.getHeroes(),
				heroesCacheSource.getHeroes()
			).map { (fetched, cached) ->
				val heroes = fetched.toMutableList()

				fetched.forEachIndexed { index, fetchedHero ->
					cached.forEach { cachedHero ->
						if (fetchedHero.id == cachedHero.id) {
							heroes[index] = fetchedHero.copy(isFavorite = true)
						}
					}
				}

				heroes
			}
				.handleErrorWith { raiseError(ContentNotAvailable) }
		}

	override fun saveHero(hero: Hero): Kind<F, Unit> =
		defer { heroesCacheSource.saveHero(hero) }
			.handleErrorWith { raiseError(SaveException) }
}