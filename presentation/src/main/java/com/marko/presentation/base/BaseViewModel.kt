package com.marko.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel(), CoroutineScope by MainScope() {

	override fun onCleared() {
		super.onCleared()

		cancel()
	}
}