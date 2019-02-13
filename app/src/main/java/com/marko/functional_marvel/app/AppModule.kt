package com.marko.functional_marvel.app

import android.content.Context
import com.karumi.marvelapiclient.CharacterApiClient
import com.karumi.marvelapiclient.ComicApiClient
import com.karumi.marvelapiclient.MarvelApiConfig
import com.marko.cache.database.MarvelDatabase
import com.marko.domain.dispatchers.CoroutineDispatchers
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
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
}