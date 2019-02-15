package com.marko.presentation

import androidx.lifecycle.Observer
import io.mockk.every
import io.mockk.mockk

inline fun <reified T : Any> stubObserver(): Observer<T> = mockk<Observer<T>>().apply {
	every { onChanged(any()) } returns Unit
}