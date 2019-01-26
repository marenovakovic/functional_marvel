package com.marko.functional_marvel.heroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marko.functional_marvel.cache.HeroesCacheSource
import com.marko.functional_marvel.cache.HeroesDao
import com.marko.functional_marvel.cache.MarvelDatabase
import com.marko.functional_marvel.data.heroes.HeroesDataSource
import com.marko.functional_marvel.data.heroes.HeroesRepositoryImpl
import com.marko.functional_marvel.domain.heroes.HeroesRepository
import com.marko.functional_marvel.injection.DI
import com.marko.functional_marvel.injection.HKImplementation
import com.marko.functional_marvel.injection.viewmodel.ViewModelFactory
import com.marko.functional_marvel.injection.viewmodel.ViewModelKey
import com.marko.functional_marvel.remote.heroes.HeroesRemoteSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
abstract class HeroesBindingModule {

	@Binds
	@Named(DI.HEROES_REMOTE_SOURCE)
	abstract fun heroRemoteSource(bind: HeroesRemoteSource<HKImplementation>): HeroesDataSource<HKImplementation>

	@Binds
	@Named(DI.HEROES_CACHE_SOURCE)
	abstract fun heroesCacheSource(bind: HeroesCacheSource<HKImplementation>): HeroesDataSource<HKImplementation>

	@Binds
	abstract fun heroesRepository(bind: HeroesRepositoryImpl<HKImplementation>): HeroesRepository<HKImplementation>

	@Binds
	@IntoMap
	@ViewModelKey(value = HeroesViewModel::class)
	abstract fun heroesViewModel(bind: HeroesViewModel): ViewModel

	@Binds
	abstract fun factory(bind: ViewModelFactory): ViewModelProvider.Factory
}

@Module(includes = [HeroesBindingModule::class])
class HeroesModule {

	@Provides
	fun heroesDao(marvelDatabase: MarvelDatabase): HeroesDao = marvelDatabase.heroesDao()
}