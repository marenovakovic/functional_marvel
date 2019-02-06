package com.marko.remote.heroes

import arrow.core.Either
import com.karumi.marvelapiclient.CharacterApiClient
import com.marko.data.entities.HeroData
import com.marko.data.entities.HeroesData
import com.marko.data.heroes.HeroesRemoteRepository
import com.marko.domain.entities.HeroId
import com.marko.remote.extenstions.safe
import com.marko.remote.mappers.toData
import javax.inject.Inject

/**
 * [HeroesRemoteRepository] implementation. Communicates with API
 *
 * @param characterApiClient [CharacterApiClient] for fetching [HeroesData]
 */
class HeroesRemoteRepositoryImpl @Inject constructor(
	private val characterApiClient: CharacterApiClient
) : HeroesRemoteRepository {

	override suspend fun fetchHeroes(): Either<Throwable, HeroesData> {
		val response = characterApiClient.getAll(0, 25).safe.map {
			it.characters.map { it.toData() }
		}

		return response.toEither()
	}

	override suspend fun fetchHero(heroId: HeroId): Either<Throwable, HeroData> {
		val response = characterApiClient.getCharacter(heroId).safe.map { it.toData() }

		return response.toEither()
	}
}