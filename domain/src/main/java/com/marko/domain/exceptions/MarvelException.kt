package com.marko.domain.exceptions

import arrow.core.None
import arrow.core.Option
import arrow.core.Some

sealed class MarvelException(message: Option<String> = None) : Throwable(message = message.orNull())

object UnsupportedCaching : MarvelException()

object SaveException : MarvelException()

object UnauthorizedAccess : MarvelException()

object NotFound : MarvelException()

object ContentNotAvailable : MarvelException()

class UnknownMarvelError(message: String) : MarvelException(message = Some(message))

val Throwable.marvelException: MarvelException
	get() = this as? MarvelException ?: UnknownMarvelError(message = message ?: "")