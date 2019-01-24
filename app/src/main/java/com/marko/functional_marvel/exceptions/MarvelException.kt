package com.marko.functional_marvel.exceptions

import arrow.core.None
import arrow.core.Option
import arrow.core.Some

sealed class MarvelException(message: Option<String> = None) : Throwable(message = message.orNull())

class UnsupportedCaching(message: String) : MarvelException(message = Some(message))

object SaveException : MarvelException()

object UnauthorizedAccess : MarvelException()

object NotFound : MarvelException()

object ContentNotAvailable : MarvelException()

class UnknownMarvelError(message: String) : MarvelException(message = Some(message))