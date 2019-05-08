package com.marko.functional_marvel.injection.fragment

import com.marko.functional_marvel.herodetails.HeroDetailsFragment
import com.marko.functional_marvel.herodetails.HeroDetailsModule
import com.marko.functional_marvel.heroes.HeroesFragment
import com.marko.functional_marvel.heroes.HeroesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

	@FragmentScope
	@ContributesAndroidInjector(modules = [HeroesModule::class])
	abstract fun heroesFragment(): HeroesFragment

	@FragmentScope
	@ContributesAndroidInjector(modules = [HeroDetailsModule::class])
	abstract fun heroDetailsFragment(): HeroDetailsFragment
}