package com.marko.domain.usecases

import arrow.core.Left
import arrow.core.Right
import arrow.effects.IO
import com.marko.domain.arrow.shouldBeLeft
import com.marko.domain.arrow.shouldBeRight
import com.marko.domain.sampledata.hero
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class ResolveFavoriteTest {

	private val setFavorite = mockk<SetFavorite>()
	private val removeFavorite = mockk<RemoveFavorite>()
	private val resolveFavorite =
		ResolveFavorite(setFavorite = setFavorite, removeFavorite = removeFavorite)

	@Test
	fun `is setFavorite use case called when hero is NOT yet favorite`() {
		val hero = hero(id = "1", isFavorite = false)

		setFavorite.stub()

		val result = resolveFavorite(parameters = hero).unsafeRunSync()

		result shouldBeRight Unit
		verify(exactly = 1) { setFavorite(parameters = hero.id) }
		verify(exactly = 0) { removeFavorite(any()) }
	}

	@Test
	fun `is result Left when setFavorites fails`() {
		val hero = hero(id = "1", isFavorite = false)
		val t = Throwable("nemeze")

		setFavorite.stubThrow(t)

		val result = resolveFavorite(parameters = hero).unsafeRunSync()

		result shouldBeLeft t
	}

	@Test
	fun `is setFavorite use case called when hero IS yet favorite`() {
		val hero = hero(id = "1", isFavorite = true)

		removeFavorite.stub()

		val result = resolveFavorite(parameters = hero).unsafeRunSync()

		result shouldBeRight Unit
		verify(exactly = 1) { removeFavorite(parameters = hero.id) }
		verify(exactly = 0) { setFavorite(any()) }
	}

	@Test
	fun `is result Left when removeFavorites fails`() {
		val hero = hero(id = "1", isFavorite = true)
		val t = Throwable("nemeze")

		removeFavorite.stubThrow(t)

		val result = resolveFavorite(parameters = hero).unsafeRunSync()

		result shouldBeLeft t
	}

	private fun SetFavorite.stub() {
		every { execute(any()) } returns IO.just(Right(Unit))
	}

	private fun SetFavorite.stubThrow(t: Throwable) {
		every { execute(any()) } returns IO.just(Left(t))
	}

	private fun RemoveFavorite.stub() {
		every { execute(any()) } returns IO.just(Right(Unit))
	}

	private fun RemoveFavorite.stubThrow(t: Throwable) {
		every { execute(any()) } returns IO.just(Left(t))
	}
}