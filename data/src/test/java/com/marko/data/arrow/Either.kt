package com.marko.data.arrow

import arrow.core.Either
import io.kotlintest.assertions.arrow.either.shouldBeLeft
import io.kotlintest.assertions.arrow.either.shouldBeRight

infix fun <L : Any, R> Either<L, R>.shouldBeRight(r: R) = this.shouldBeRight(r)

infix fun <L, R : Any> Either<L, R>.shouldBeLeft(r: R) = this.shouldBeLeft(r)