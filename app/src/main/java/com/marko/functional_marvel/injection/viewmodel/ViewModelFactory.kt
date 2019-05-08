package com.marko.functional_marvel.injection.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * [ViewModelProvider.Factory] modified to support [ViewModel] constructor injection
 *
 * @param viewModelMap [Map<Class<out ViewModel>, Provider<ViewModel>>] containing [ViewModel]s that can be injected
 */
class ViewModelFactory @Inject constructor(
	private val viewModelMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T =
		viewModelMap[modelClass]?.get() as T
}