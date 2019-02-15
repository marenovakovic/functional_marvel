package com.marko.presentation.herodetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Left
import arrow.core.Right
import arrow.effects.IO
import com.marko.domain.entities.HeroEntity
import com.marko.domain.exceptions.MarvelException
import com.marko.domain.exceptions.NotFound
import com.marko.domain.usecases.FetchHero
import com.marko.domain.usecases.invoke
import com.marko.presentation.TestCoroutineDispatchers
import com.marko.presentation.entities.Hero
import com.marko.presentation.mappers.toPresentation
import com.marko.presentation.sampledata.heroEntity
import com.marko.presentation.stubObserver
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HeroDetailsViewModelTest {

	@get:Rule
	val rule = InstantTaskExecutorRule()

	private val dispatchers = TestCoroutineDispatchers()
	private val fetchHero = mockk<FetchHero>()
	private val viewModel = HeroDetailsViewModel(dispatchers = dispatchers,fetchHero = fetchHero)

	@Test
	fun `is fetchHero called`() {
		val heroId = "1"
		val hero = heroEntity()

		fetchHero.stubHero(hero)

		viewModel.fetch(heroId = heroId)

		verify(exactly = 1) { fetchHero(heroId) }
	}

	@Test
	fun `check live data value after fetch is called`() {
		val heroId = "1"
		val hero = heroEntity(id = heroId)

		val observer = stubObserver<Hero>()
		viewModel.hero.observeForever(observer)

		fetchHero.stubHero(hero)

		viewModel.fetch(heroId = heroId)

		verify(exactly = 1) { observer.onChanged(hero.toPresentation()) }
	}

	@Test
	fun `check live data value when fetch fails`() {
		val e = NotFound

		fetchHero.stubThrow(e)

		val observer = stubObserver<MarvelException>()
		viewModel.error.observeForever(observer)

		viewModel.fetch(heroId = "1")

		verify(exactly = 1) { observer.onChanged(e) }
	}

	private fun FetchHero.stubHero(heroEntity: HeroEntity) {
		every { execute(any()) } returns IO.just(Right(heroEntity))
	}

	private fun FetchHero.stubThrow(e: MarvelException) {
		every { execute(any()) } returns IO.just(Left(e))
	}
}