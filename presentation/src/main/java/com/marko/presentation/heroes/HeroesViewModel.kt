package com.marko.presentation.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import arrow.effects.extensions.io.unsafeRun.runNonBlocking
import arrow.unsafe
import com.marko.domain.dispatchers.CoroutineDispatchers
import com.marko.domain.entities.HeroesEntity
import com.marko.domain.exceptions.MarvelException
import com.marko.domain.exceptions.marvelException
import com.marko.domain.usecases.FetchHeroes
import com.marko.domain.usecases.invoke
import com.marko.presentation.base.BaseViewModel
import com.marko.presentation.entities.Heroes
import com.marko.presentation.mappers.toPresentation
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroesViewModel @Inject constructor(
	private val dispatchers: CoroutineDispatchers,
	private val fetchHeroes: FetchHeroes
) : BaseViewModel(dispatchers) {

	/**
	 * [MutableLiveData] holding loading state [Boolean], exposed as [LiveData]
	 */
	private val _loading = MutableLiveData<Boolean>()
	val loading: LiveData<Boolean>
		get() = _loading

	/**
	 * [MutableLiveData] holding [Heroes] from success fetching result, exposed as [LiveData]
	 */
	private val _heroes = MutableLiveData<Heroes>()
	val heroes: LiveData<Heroes>
		get() = _heroes

	/**
	 * [MutableLiveData] holding [Throwable] thrown during fetching, exposed as [LiveData]
	 */
	private val _error = MutableLiveData<MarvelException>()
	val error: LiveData<MarvelException>
		get() = _error

	/**
	 * Start the fetching flow
	 */
	fun fetch() {
		launch(dispatchers.io) {
			unsafe { runNonBlocking({ fetchHeroes() }, ::handleHeroesResult) }
		}
	}

	private fun handleHeroesResult(result: Either<Throwable, Either<Throwable, HeroesEntity>>) {
		result.fold({ _error.postValue(it.marvelException) }, ::handleHeroes)
	}

	private fun handleHeroes(heroes: Either<Throwable, HeroesEntity>) {
		heroes.fold(
			{ _error.postValue(it.marvelException) },
			{ _heroes.postValue(it.toPresentation()) }
		)
	}
}