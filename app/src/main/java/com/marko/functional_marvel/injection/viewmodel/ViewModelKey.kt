package com.marko.functional_marvel.injection.viewmodel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * [ViewModel] key that enables putting [ViewModel] into [Map] what enables [ViewModel] constructor injection
 *
 * @param value [ViewModel] [Class] to be put into [Map] so it can be used to make [ViewModelFactory] for given [ViewModel] [Class]
 */
@MapKey
@Retention(AnnotationRetention.RUNTIME)
@Target(
	AnnotationTarget.FUNCTION,
	AnnotationTarget.PROPERTY_GETTER,
	AnnotationTarget.PROPERTY_SETTER
)
annotation class ViewModelKey(val value: KClass<out ViewModel>)