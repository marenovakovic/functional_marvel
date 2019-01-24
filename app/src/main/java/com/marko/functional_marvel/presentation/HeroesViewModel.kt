package com.marko.functional_marvel.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.effects.unsafeRunAsync
import com.marko.functional_marvel.base.BaseViewModel
import com.marko.functional_marvel.entities.Hero
import com.marko.functional_marvel.entities.Heroes
import com.marko.functional_marvel.injection.HKImplementation
import com.marko.functional_marvel.usecases.FetchHeroes
import com.marko.functional_marvel.usecases.SetFavorite
import com.marko.functional_marvel.usecases.invoke
import javax.inject.Inject

/**
 * Loads [Heroes] data and exposes it to the view with [LiveData].
 */
class HeroesViewModel @Inject constructor(
	private val fetchHeroes: FetchHeroes<HKImplementation>,
	private val setFavorite: SetFavorite<HKImplementation>
) : BaseViewModel() {

	/**
	 * [MutableLiveData] holding [Heroes] from success fetching result, exposed as [LiveData]
	 */
	private val _heroes = MutableLiveData<Heroes>()
	val heroes: LiveData<Heroes>
		get() = _heroes

	/**
	 * [MutableLiveData] holding [Throwable] thrown during fetching, exposed as [LiveData]
	 */
	private val _error = MutableLiveData<Throwable>()
	val error: LiveData<Throwable>
		get() = _error

	fun fetch() =
		fetchHeroes().unsafeRunAsync {
			it.fold(_error::postValue, _heroes::postValue)
		}

	fun saveHero(hero: Hero) =
		setFavorite(hero).unsafeRunAsync {
			it.fold(_error::postValue) { }
		}
}