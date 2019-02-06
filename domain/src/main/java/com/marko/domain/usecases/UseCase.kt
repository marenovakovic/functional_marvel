package com.marko.domain.usecases

import arrow.core.Either

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
	 * @return [Either] containing either result of executed business logic [R] or [Throwable] if something goes wrong
	 */
	abstract suspend fun execute(parameters: P): Either<Throwable, R>
}

/**
 * Execute use case that takes no arguments
 *
 * [R] result of business logic execution
 *
 * @return [Either] containing either result of executed business logic [R] or [Throwable] if something goes wrong
 */
suspend operator fun <R> UseCase<Unit, R>.invoke() = this.execute(Unit)