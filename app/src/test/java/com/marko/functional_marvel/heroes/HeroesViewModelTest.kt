package com.marko.functional_marvel.heroes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import arrow.effects.ObservableK
import com.marko.functional_marvel.domain.heroes.HeroesRepository
import com.marko.functional_marvel.entities.Heroes
import com.marko.functional_marvel.injection.HKImplementation
import com.marko.functional_marvel.sampledata.sampleHeroes
import com.marko.functional_marvel.usecases.FetchHeroes
import com.marko.functional_marvel.usecases.SetFavorite
import com.marko.functional_marvel.usecases.invoke
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class HeroesViewModelTest {

	@get:Rule
	val instantExecution = InstantTaskExecutorRule()

	private val heroesRepository = mockk<HeroesRepository<HKImplementation>>()
	private val fetchHeroes = FetchHeroes(heroesRepository)
	private val setFavorite = SetFavorite(heroesRepository)
	private val viewModel = HeroesViewModel(fetchHeroes, setFavorite)

	@Test
	fun `test does fetch heroes exposes right result on success and does it calls use case`() {
		val observer = stubObserver<Heroes>()
		viewModel.heroes.observeForever(observer)

		val errorObserver = stubObserver<Throwable>()
		viewModel.error.observeForever(errorObserver)

		val stubHeroes = sampleHeroes
		heroesRepository.stubHeroes(stubHeroes)

		viewModel.fetch()

		verify(exactly = 1) { fetchHeroes() }
		verify(exactly = 1) { observer.onChanged(stubHeroes) }
		verify(exactly = 0) { errorObserver.onChanged(any()) }
	}

	@Test
	fun `test does fetch heroes exposes right result on error and does it calls use case`() {
		val observer = stubObserver<Heroes>()
		viewModel.heroes.observeForever(observer)

		val t = Throwable("exception")
		heroesRepository.stubThrow(t)

		val errorObserver = stubObserver<Throwable>()
		viewModel.error.observeForever(errorObserver)

		viewModel.fetch()

		verify(exactly = 1) { fetchHeroes() }
		verify(exactly = 0) { observer.onChanged(any()) }
		verify(exactly = 1) { errorObserver.onChanged(any()) }
	}

	private fun HeroesRepository<HKImplementation>.stubHeroes(heroes: Heroes) {
		every { getHeroes() } returns ObservableK.just(heroes)
	}

	private fun HeroesRepository<HKImplementation>.stubThrow(t: Throwable) {
		every { getHeroes() } returns ObservableK.raiseError(t)
	}

	private inline fun <reified A : Any> stubObserver(): Observer<A> = mockk<Observer<A>>().apply {
		every { onChanged(any()) } returns Unit
	}
}