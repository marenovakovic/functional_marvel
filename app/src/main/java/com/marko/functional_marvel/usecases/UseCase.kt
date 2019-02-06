package com.marko.functional_marvel.usecases

import arrow.Kind

/**
 * Implement this to execute business logic
 *
 * [F] Higher-kind type
 * [P] parameters need to execute business logic
 * [R] result of business logic execution
 */
interface UseCase<out F, in P, out R> {

	/**
	 * Business logic is executed here
	 *
	 * @param parameters [P] parameters needed for execution
	 *
	 * @return [Kind] containing result of executed business logic
	 */
	fun execute(parameters: P): Kind<F, R>
}

/**
 * Execute use case
 *
 * [F] Higher-kind type
 * [P] parameters needed for execution
 * [R] result of business logic execution
 *
 * @param parameters [P] parameters needed for execution
 *
 * @return [Kind] containing result of executed business logic
 */
operator fun <F, P, R> UseCase<F, P, R>.invoke(parameters: P) = this.execute(parameters)

/**
 * Execute use case that takes no arguments
 *
 * [F] Higher-kind type
 * [R] result of business logic execution
 *
 * @return [Kind] containing result of executed business logic
 */
operator fun <F, R> UseCase<F, Unit, R>.invoke() = this.execute(Unit)