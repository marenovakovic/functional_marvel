package com.marko.remote.extenstions

import arrow.core.Failure
import arrow.core.Success
import arrow.core.Try
import com.karumi.marvelapiclient.model.MarvelResponse
import com.marko.domain.exceptions.MarvelException
import com.marko.domain.exceptions.NotFound
import com.marko.domain.exceptions.UnauthorizedAccess
import com.marko.domain.exceptions.UnknownMarvelError

val <A> MarvelResponse<A>.safe: Try<A>
	get() = when (code) {
		200 -> Success(response)
		else -> Failure(marvelError)
	}

val <A> MarvelResponse<A>.marvelError: MarvelException
	get() = when (code) {
		404 -> NotFound
		401 -> UnauthorizedAccess
		else -> UnknownMarvelError(message = status)
	}