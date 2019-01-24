package com.marko.functional_marvel.base

import dagger.android.support.DaggerFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseFragment : DaggerFragment(), CoroutineScope by MainScope() {

	override fun onDestroy() {
		super.onDestroy()

		cancel()
	}
}