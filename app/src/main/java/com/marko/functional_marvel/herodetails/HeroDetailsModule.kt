package com.marko.functional_marvel.herodetails

import androidx.lifecycle.ViewModel
import com.marko.functional_marvel.injection.viewmodel.ViewModelKey
import com.marko.presentation.herodetails.HeroDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HeroDetailsModule {

	@Binds
	@IntoMap
	@ViewModelKey(value = HeroDetailsViewModel::class)
	abstract fun heroDetailsViewModel(bind: HeroDetailsViewModel): ViewModel
}