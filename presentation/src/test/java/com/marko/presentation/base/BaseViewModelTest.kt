package com.marko.presentation.base

import com.marko.presentation.TestCoroutineDispatchers
import kotlinx.coroutines.Job
import org.junit.jupiter.api.Test

internal class BaseViewModelTest {

	private val viewModel = object : BaseViewModel(TestCoroutineDispatchers()) {
		fun fetch() = Unit
	}

	@Test
	fun `is job canceled when onCleared is called`() {
		val job = viewModel.coroutineContext[Job]

		viewModel
	}
}