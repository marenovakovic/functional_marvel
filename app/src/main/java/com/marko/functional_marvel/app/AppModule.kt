package com.marko.functional_marvel.app

import android.content.Context
import arrow.effects.ObservableK
import arrow.effects.observablek.async.async
import arrow.effects.observablek.monadDefer.monadDefer
import arrow.effects.typeclasses.Async
import arrow.effects.typeclasses.MonadDefer
import com.karumi.marvelapiclient.CharacterApiClient
import com.karumi.marvelapiclient.ComicApiClient
import com.karumi.marvelapiclient.MarvelApiConfig
import com.marko.functional_marvel.cache.MarvelDatabase
import com.marko.functional_marvel.injection.HKImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

	@Provides
	fun context(): Context = context

	@Singleton
	@Provides
	fun marvelApiConfig(): MarvelApiConfig =
		MarvelApiConfig.with(
			"a5df0eadce2633ac1a02a9842284d454",
			"36a01c37613b4ed4efffca64708be3b6adbbbd58"
		)

	@Singleton
	@Provides
	fun charactersClient(config: MarvelApiConfig): CharacterApiClient = CharacterApiClient(config)

	@Singleton
	@Provides
	fun comicClient(config: MarvelApiConfig): ComicApiClient = ComicApiClient(config)

	@Singleton
	@Provides
	fun marvelDatabase(context: Context): MarvelDatabase = MarvelDatabase.getInstance(context)

	@Singleton
	@Provides
	fun async(): Async<HKImplementation> = ObservableK.async()

	@Singleton
	@Provides
	fun monadDefer(): MonadDefer<HKImplementation> = ObservableK.monadDefer()
}