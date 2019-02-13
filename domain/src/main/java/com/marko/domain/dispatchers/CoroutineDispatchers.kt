package com.marko.domain.dispatchers

import kotlin.coroutines.CoroutineContext

interface CoroutineDispatchers {

	val main: CoroutineContext
	val io: CoroutineContext
}