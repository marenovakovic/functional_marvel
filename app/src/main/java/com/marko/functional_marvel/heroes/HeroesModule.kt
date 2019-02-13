package com.marko.functional_marvel.heroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marko.cache.database.MarvelDatabase
import com.marko.cache.heroes.HeroesCacheRepositoryImpl
import com.marko.cache.heroes.HeroesDao
import com.marko.data.heroes.*
import com.marko.data.injection.DI
import com.marko.domain.heroes.HeroesRepository
import com.marko.functional_marvel.injection.viewmodel.ViewModelFactory
import com.marko.functional_marvel.injection.viewmodel.ViewModelKey
import com.marko.presentation.heroes.HeroesViewModel
import com.marko.remote.heroes.HeroesRemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
abstract class HeroesBindingModule {

	@Binds
	abstract fun heroesRemoteRepository(bind: HeroesRemoteRepositoryImpl): HeroesRemoteRepository

	@Binds
	abstract fun heroesCacheRepository(bind: HeroesCacheRepositoryImpl): HeroesCacheRepository

	@Binds
	@Named(DI.REMOTE_SOURCE)
	abstract fun heroRemoteSource(bind: HeroesRemoteSource): HeroesDataSource

	@Binds
	@Named(DI.CACHE_SOURCE)
	abstract fun heroesCacheSource(bind: HeroesCacheSource): HeroesDataSource

	@Binds
	abstract fun heroesRepository(bind: HeroesRepositoryImpl): HeroesRepository

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