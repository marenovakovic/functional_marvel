package com.marko.functional_marvel.injection.fragment

import com.marko.functional_marvel.heroes.HeroesFragment
import com.marko.functional_marvel.heroes.HeroesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

	@FragmentScope
	@ContributesAndroidInjector(modules = [HeroesModule::class])
	abstract fun heroesFragment(): HeroesFragment
}