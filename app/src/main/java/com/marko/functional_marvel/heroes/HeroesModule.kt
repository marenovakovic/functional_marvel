package com.marko.functional_marvel.heroes

import androidx.lifecycle.ViewModel
import com.marko.functional_marvel.injection.viewmodel.ViewModelKey
import com.marko.presentation.heroes.HeroesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HeroesModule {

	@Binds
	@IntoMap
	@ViewModelKey(value = HeroesViewModel::class)
	abstract fun heroesViewModel(bind: HeroesViewModel): ViewModel
}