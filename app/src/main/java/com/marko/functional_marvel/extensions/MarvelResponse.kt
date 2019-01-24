package com.marko.functional_marvel.extensions

import arrow.core.Failure
import arrow.core.Success
import arrow.core.Try
import com.karumi.marvelapiclient.model.MarvelResponse
import com.marko.functional_marvel.exceptions.MarvelException
import com.marko.functional_marvel.exceptions.NotFound
import com.marko.functional_marvel.exceptions.UnauthorizedAccess
import com.marko.functional_marvel.exceptions.UnknownMarvelError

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