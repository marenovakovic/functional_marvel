package com.marko.presentation

import com.marko.domain.dispatchers.CoroutineDispatchers
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestCoroutineDispatchers : CoroutineDispatchers {

	override val main: CoroutineContext
		get() = Dispatchers.Unconfined

	override val io: CoroutineContext
		get() = Dispatchers.Unconfined
}