package com.marko.presentation.base

import androidx.lifecycle.ViewModel
import com.marko.domain.dispatchers.CoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * Base [ViewModel] class providing [CoroutineScope] by [MainScope] and manages [Job]
 *
 * @param dispatchers [CoroutineDispatchers] so every [ViewModel] extending this will have [CoroutineDispatchers]
 */
abstract class BaseViewModel(
	dispatchers: CoroutineDispatchers
) : ViewModel(), CoroutineScope by MainScope() {

	/**
	 * Cancel [Job] because we don't need it any more
	 */
	override fun onCleared() {
		super.onCleared()

		cancel()
	}
}