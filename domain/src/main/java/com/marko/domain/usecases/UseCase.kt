package com.marko.domain.usecases

import arrow.core.Either
import arrow.effects.IO

/**
 * Implement this to execute business logic
 *
 * [P] parameters need to execute business logic
 * [R] result of business logic execution
 */
abstract class UseCase<in P, out R> {

	/**
	 * Business logic is executed here
	 *
	 * @param parameters [P] parameters needed for execution
	 *
	 * @return [IO] with [Either] containing either result of executed business logic [R] or [Throwable] if something goes wrong
	 */
	abstract fun execute(parameters: P): IO<Either<Throwable, R>>
}

/**
 * Execute use case that takes arguments
 *
 * [T] parameters need for use case execution
 * [R] result of business logic execution
 *
 * @param parameters [T] parameters needed for use case execution
 *
 * @return [IO] with [Either] containing either result of executed business logic [R] or [Throwable] if something goes wrong
 */
operator fun <T, R> UseCase<T, R>.invoke(parameters: T): IO<Either<Throwable, R>> = this.execute(parameters)

/**
 * Execute use case that takes no arguments
 *
 * [R] result of business logic execution
 *
 * @return [IO] with [Either] containing either result of executed business logic [R] or [Throwable] if something goes wrong
 */
operator fun <R> UseCase<Unit, R>.invoke(): IO<Either<Throwable, R>> = this.execute(Unit)