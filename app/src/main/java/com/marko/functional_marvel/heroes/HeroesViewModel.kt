package com.marko.functional_marvel.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marko.domain.usecases.FetchHeroes
import com.marko.domain.usecases.invoke
import com.marko.functional_marvel.base.BaseViewModel
import com.marko.functional_marvel.entities.Hero
import com.marko.functional_marvel.entities.Heroes
import com.marko.functional_marvel.injection.HKImplementation
import com.marko.functional_marvel.usecases.SetFavorite
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Loads [Heroes] data and exposes it to the view with [LiveData].
 */
class HeroesViewModel @Inject constructor(
	private val fetchHeroes: FetchHeroes,
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

	/**
	 * Start the fetching flow
	 */
	fun fetch() = launch {
		fetchHeroes()
			.map { listOf<Hero>() }
			.fold(_error::postValue, _heroes::postValue)
	}
}