package com.marko.functional_marvel.herodetails

import androidx.lifecycle.ViewModel
import com.marko.data.comics.ComicsDataSource
import com.marko.data.comics.ComicsRemoteRepository
import com.marko.data.comics.ComicsRemoteSource
import com.marko.data.comics.ComicsRepositoryImpl
import com.marko.domain.comics.ComicsRepository
import com.marko.functional_marvel.injection.viewmodel.ViewModelKey
import com.marko.presentation.herodetails.HeroDetailsViewModel
import com.marko.remote.comics.ComicsRemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HeroDetailsModule {

	@Binds
	@IntoMap
	@ViewModelKey(value = HeroDetailsViewModel::class)
	abstract fun heroDetailsViewModel(bind: HeroDetailsViewModel): ViewModel

	@Binds
	abstract fun comicsRepository(bind: ComicsRepositoryImpl): ComicsRepository

	@Binds
	abstract fun comicsRemoteSource(bind: ComicsRemoteSource): ComicsDataSource

	@Binds
	abstract fun comicsRemoteRepository(bind: ComicsRemoteRepositoryImpl): ComicsRemoteRepository
}