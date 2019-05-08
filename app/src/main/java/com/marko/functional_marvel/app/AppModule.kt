package com.marko.functional_marvel.app

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.karumi.marvelapiclient.CharacterApiClient
import com.karumi.marvelapiclient.ComicApiClient
import com.karumi.marvelapiclient.MarvelApiConfig
import com.karumi.marvelapiclient.SeriesApiClient
import com.marko.cache.database.MarvelDatabase
import com.marko.cache.heroes.HeroesCacheRepositoryImpl
import com.marko.cache.heroes.HeroesDao
import com.marko.data.heroes.*
import com.marko.data.injection.DI
import com.marko.domain.dispatchers.CoroutineDispatchers
import com.marko.domain.heroes.HeroesRepository
import com.marko.functional_marvel.injection.viewmodel.ViewModelFactory
import com.marko.remote.heroes.HeroesRemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
abstract class AppBindingModule {

	@Singleton
	@Binds
	abstract fun factory(bind: ViewModelFactory): ViewModelProvider.Factory

	@Singleton
	@Binds
	abstract fun heroesRemoteRepository(bind: HeroesRemoteRepositoryImpl): HeroesRemoteRepository

	@Singleton
	@Binds
	abstract fun heroesCacheRepository(bind: HeroesCacheRepositoryImpl): HeroesCacheRepository

	@Singleton
	@Binds
	@Named(DI.HEROES_REMOTE_SOURCE)
	abstract fun heroRemoteSource(bind: HeroesRemoteSource): HeroesDataSource

	@Singleton
	@Binds
	@Named(DI.HEROES_CACHE_SOURCE)
	abstract fun heroesCacheSource(bind: HeroesCacheSource): HeroesDataSource

	@Singleton
	@Binds
	abstract fun heroesRepository(bind: HeroesRepositoryImpl): HeroesRepository
}

@Module(includes = [AppBindingModule::class])
class AppModule(private val context: Context) {

	@Provides
	fun context(): Context = context

	@Singleton
	@Provides
	fun dispatchers(): CoroutineDispatchers = object : CoroutineDispatchers {
		override val main: CoroutineContext
			get() = Dispatchers.Main

		override val io: CoroutineContext
			get() = Dispatchers.IO
	}

	@Singleton
	@Provides
	fun marvelApiConfig(): MarvelApiConfig =
		MarvelApiConfig.with(
			"",
			""
		)

	@Singleton
	@Provides
	fun marvelDatabase(context: Context): MarvelDatabase = MarvelDatabase.getInstance(context)

	@Singleton
	@Provides
	fun heroesDao(marvelDatabase: MarvelDatabase): HeroesDao = marvelDatabase.heroesDao()

	@Singleton
	@Provides
	fun charactersClient(config: MarvelApiConfig): CharacterApiClient = CharacterApiClient(config)

	@Singleton
	@Provides
	fun comicClient(config: MarvelApiConfig): ComicApiClient = ComicApiClient(config)

	@Singleton
	@Provides
	fun seriesClient(config: MarvelApiConfig): SeriesApiClient = SeriesApiClient(config)
}