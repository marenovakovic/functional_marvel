package com.marko.presentation.herodetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import arrow.effects.extensions.io.applicativeError.handleError
import arrow.effects.extensions.io.fx.fx
import arrow.effects.extensions.io.unsafeRun.runNonBlocking
import arrow.unsafe
import com.marko.domain.dispatchers.CoroutineDispatchers
import com.marko.domain.entities.ComicsEntity
import com.marko.domain.entities.HeroEntity
import com.marko.domain.entities.HeroId
import com.marko.domain.exceptions.MarvelException
import com.marko.domain.exceptions.marvelException
import com.marko.domain.usecases.FetchComicsForHero
import com.marko.domain.usecases.FetchHero
import com.marko.domain.usecases.invoke
import com.marko.presentation.base.BaseViewModel
import com.marko.presentation.entities.Hero
import com.marko.presentation.mappers.toPresentation
import javax.inject.Inject

/**
 * Fetches [Hero] related data and exposes it to the view
 *
 * @param dispatchers [CoroutineDispatchers]
 *
 * @param fetchHero [FetchHero] use case for fetching [Hero]
 */
class HeroDetailsViewModel @Inject constructor(
	private val dispatchers: CoroutineDispatchers,
	private val fetchHero: FetchHero,
	private val fetchComicsForHero: FetchComicsForHero
) : BaseViewModel(dispatchers) {

	/**
	 * [MutableLiveData] holding loading state [Hero], exposed as [LiveData]
	 */
	private val _hero = MutableLiveData<Hero>()
	val hero: LiveData<Hero>
		get() = _hero

	/**
	 * [MutableLiveData] holding loading state [MarvelException], exposed as [LiveData]
	 */
	private val _error = MutableLiveData<MarvelException>()
	val error: LiveData<MarvelException>
		get() = _error

	/**
	 * Start the fetching flow
	 *
	 * @param heroId [HeroId]
	 */
	fun fetch(heroId: HeroId) {
		val flow = fx {
			val heroesFiber =
				! dispatchers.io.startFiber(! effect { fetchHero(parameters = heroId) })
			val comicsFiber =
				! dispatchers.io.startFiber(! effect { fetchComicsForHero(parameters = heroId) })

			val hero = ! heroesFiber.join()
			val comics = ! comicsFiber.join()

			handleHero(hero)
			handleComics(comics)
		}.handleError { _error.postValue(it.marvelException) }

		unsafe { runNonBlocking({ flow }) { } }
	}

	private fun handleHero(hero: Either<Throwable, HeroEntity>) {
		hero.fold(
			{ _error.postValue(it.marvelException) },
			{ _hero.postValue(it.toPresentation()) })
	}

	private fun handleComics(comics: Either<Throwable, ComicsEntity>) {
		comics.fold({}, ::println)
	}
}