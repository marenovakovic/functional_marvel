package com.marko.presentation.herodetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import arrow.effects.extensions.io.fx.fx
import arrow.effects.extensions.io.unsafeRun.runNonBlocking
import arrow.unsafe
import com.marko.domain.dispatchers.CoroutineDispatchers
import com.marko.domain.entities.ComicsEntity
import com.marko.domain.entities.HeroEntity
import com.marko.domain.entities.HeroId
import com.marko.domain.entities.SeriesEntity
import com.marko.domain.exceptions.MarvelException
import com.marko.domain.exceptions.marvelException
import com.marko.domain.usecases.FetchComicsForHero
import com.marko.domain.usecases.FetchHero
import com.marko.domain.usecases.FetchSeriesForHero
import com.marko.domain.usecases.invoke
import com.marko.presentation.base.BaseViewModel
import com.marko.presentation.entities.Comics
import com.marko.presentation.entities.Hero
import com.marko.presentation.entities.Series
import com.marko.presentation.mappers.toPresentation
import javax.inject.Inject

/**
 * Fetches [Hero] related data and exposes it to the view
 *
 * @param dispatchers [CoroutineDispatchers]
 *
 * @param fetchHero [FetchHero] use case for fetching [Hero]
 *
 * @param fetchComicsForHero [FetchComicsForHero] use case for fetching [Hero] related [Comics]
 *
 * @param fetchSeriesForHero [FetchSeriesForHero] use case for fetching [Hero] related [Series]
 */
class HeroDetailsViewModel @Inject constructor(
	private val dispatchers: CoroutineDispatchers,
	private val fetchHero: FetchHero,
	private val fetchComicsForHero: FetchComicsForHero,
	private val fetchSeriesForHero: FetchSeriesForHero
) : BaseViewModel(dispatchers) {

	/**
	 * [MutableLiveData] holding [Hero], exposed as [LiveData]
	 */
	private val _hero = MutableLiveData<Hero>()
	val hero: LiveData<Hero>
		get() = _hero

	/**
	 * [MutableLiveData] holding [Comics], exposed as [LiveData]
	 */
	private val _comics = MutableLiveData<Comics>()
	val comics: LiveData<Comics>
		get() = _comics

	/**
	 * [MutableLiveData] holding [Series] [List], exposed as [LiveData]
	 */
	private val _series = MutableLiveData<List<Series>>()
	val series: LiveData<List<Series>>
		get() = _series

	/**
	 * [MutableLiveData] holding [MarvelException], exposed as [LiveData]
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
		val heroFlow =
			fx { ! ! dispatchers.io.effect { fetchHero(parameters = heroId) } }
		val comicsFlow =
			fx { ! ! dispatchers.io.effect { fetchComicsForHero(parameters = heroId) } }
		val seriesFlow =
			fx { ! ! dispatchers.io.effect { fetchSeriesForHero(parameters = heroId) } }

		unsafe { runNonBlocking({ heroFlow }) { it.fold({}, ::handleHero) } }
		unsafe { runNonBlocking({ comicsFlow }) { it.fold({}, ::handleComics) } }
		unsafe { runNonBlocking({ seriesFlow }) { it.fold({}, ::handleSeries) } }
	}

	/**
	 * Check if received [Either] contains [Throwable] or [HeroEntity]
	 *
	 * @param hero [Either] containing [Throwable] or [HeroEntity]
	 */
	private fun handleHero(hero: Either<Throwable, HeroEntity>) {
		hero.fold(
			{ _error.postValue(it.marvelException) },
			{ _hero.postValue(it.toPresentation()) }
		)
	}

	/**
	 * Check if received [Either] contains [Throwable] or [ComicsEntity]
	 *
	 * @param comics [Either] containing [Throwable] or [ComicsEntity]
	 */
	private fun handleComics(comics: Either<Throwable, ComicsEntity>) {
		comics.fold(
			{ _error.postValue(it.marvelException) },
			{ _comics.postValue(it.toPresentation()) }
		)
	}

	/**
	 * Check if received [Either] contains [Throwable] or [SeriesEntity] [List]
	 *
	 * @param series [Either] containing [Throwable] or [SeriesEntity] [List]
	 */
	private fun handleSeries(series: Either<Throwable, List<SeriesEntity>>) {
		series.fold(
			{ _error.postValue(it.marvelException) },
			{ _series.postValue(it.toPresentation()) }
		)
	}
}