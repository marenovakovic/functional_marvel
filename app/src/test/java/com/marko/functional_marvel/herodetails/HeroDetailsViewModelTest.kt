package com.marko.functional_marvel.herodetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import arrow.effects.ObservableK
import com.marko.functional_marvel.entities.Comics
import com.marko.functional_marvel.injection.HKImplementation
import com.marko.functional_marvel.sampledata.sampleComics
import com.marko.functional_marvel.usecases.FetchComics
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class HeroDetailsViewModelTest {

	@get:Rule
	val instantExecutionRule = InstantTaskExecutorRule()

	private val fetchComics = mockk<FetchComics<HKImplementation>>()
	private val viewModel = HeroDetailsViewModel(fetchComics = fetchComics)

	@Test
	fun `check does live data holds correct result and does view model calls use case`() {
		val comics = sampleComics
		fetchComics.stubComics(comics = comics)

		val comicsObserver = stubObserver<Comics>()
		viewModel.comics.observeForever(comicsObserver)

		val errorObserver = stubObserver<Throwable>()
		viewModel.error.observeForever(errorObserver)

		viewModel.fetch()

		verify(exactly = 1) { comicsObserver.onChanged(comics) }
		verify(exactly = 1) { comicsObserver.onChanged(comics) }
	}

	private fun FetchComics<*>.stubComics(comics: Comics) {
		every { execute(Unit) } returns ObservableK.just(comics)
	}

	private inline fun <reified A : Any> stubObserver(): Observer<A> = mockk<Observer<A>>().apply {
		every { onChanged(any()) } returns Unit
	}
}