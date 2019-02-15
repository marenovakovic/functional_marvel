package com.marko.presentation.herodetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Either
import arrow.effects.extensions.io.unsafeRun.runNonBlocking
import arrow.unsafe
import com.marko.domain.dispatchers.CoroutineDispatchers
import com.marko.domain.entities.HeroEntity
import com.marko.domain.entities.HeroId
import com.marko.domain.exceptions.MarvelException
import com.marko.domain.exceptions.marvelException
import com.marko.domain.usecases.FetchHero
import com.marko.domain.usecases.invoke
import com.marko.presentation.base.BaseViewModel
import com.marko.presentation.entities.Hero
import com.marko.presentation.mappers.toPresentation
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroDetailsViewModel @Inject constructor(
	private val dispatchers: CoroutineDispatchers,
	private val fetchHero: FetchHero
) : BaseViewModel(dispatchers) {

	private val _hero = MutableLiveData<Hero>()
	val hero: LiveData<Hero>
		get() = _hero

	private val _error = MutableLiveData<MarvelException>()
	val error: LiveData<MarvelException>
		get() = _error

	fun fetch(heroId: HeroId) {
		launch(dispatchers.io) {
			unsafe { runNonBlocking({ fetchHero(heroId) }, ::handleHeroResult) }
		}
	}

	private fun handleHeroResult(result: Either<Throwable, Either<Throwable, HeroEntity>>) {
		result.fold({ _error.postValue(it.marvelException) }, ::handleHero)
	}

	private fun handleHero(hero: Either<Throwable, HeroEntity>) {
		hero.fold(
			{ _error.postValue(it.marvelException) },
			{ _hero.postValue(it.toPresentation()) })
	}
}