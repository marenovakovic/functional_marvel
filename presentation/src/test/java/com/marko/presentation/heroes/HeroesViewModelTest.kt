package com.marko.presentation.heroes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Left
import arrow.core.Right
import arrow.effects.IO
import com.marko.domain.entities.HeroesEntity
import com.marko.domain.exceptions.MarvelException
import com.marko.domain.exceptions.NotFound
import com.marko.domain.usecases.FetchHeroes
import com.marko.domain.usecases.invoke
import com.marko.presentation.TestCoroutineDispatchers
import com.marko.presentation.entities.Heroes
import com.marko.presentation.mappers.toPresentation
import com.marko.presentation.sampledata.heroesEntity
import com.marko.presentation.stubObserver
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
internal class HeroesViewModelTest {

	@get:Rule
	val rule = InstantTaskExecutorRule()

	private val dispatchers = TestCoroutineDispatchers()
	private val fetchHeroes = mockk<FetchHeroes>()
	private val viewModel = HeroesViewModel(dispatchers = dispatchers, fetchHeroes = fetchHeroes)

	@Test
	fun `does fetch calls usecase`() = runBlocking {
		val heroes = heroesEntity

		fetchHeroes.stubHeroes(heroes)

		viewModel.fetch()

		coVerify(exactly = 1) { fetchHeroes() }
	}

	@Test
	fun `check live data value after fetch is called`() = runBlocking {
		val heroes = heroesEntity

		val observer = stubObserver<Heroes>()
		viewModel.heroes.observeForever(observer)

		fetchHeroes.stubHeroes(heroes)

		viewModel.fetch()

		coVerify(exactly = 1) { observer.onChanged(heroes.toPresentation()) }
	}

	@Test
	fun `check live data value when fetch fails`() = runBlocking {
		val t = NotFound

		val observer = stubObserver<MarvelException>()
		viewModel.error.observeForever(observer)

		fetchHeroes.stubThrow(t)

		viewModel.fetch()

		coVerify(exactly = 1) { observer.onChanged(t) }
	}

	private fun FetchHeroes.stubHeroes(heroes: HeroesEntity) {
		coEvery { execute(Unit) } returns IO.just(Right(heroes))
	}

	private fun FetchHeroes.stubThrow(e: MarvelException) {
		coEvery { execute(Unit) } returns IO.just(Left(e))
	}
}