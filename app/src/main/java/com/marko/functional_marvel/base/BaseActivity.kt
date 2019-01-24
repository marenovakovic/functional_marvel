package com.marko.functional_marvel.base

import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseActivity : DaggerAppCompatActivity(), CoroutineScope by MainScope() {

	override fun onDestroy() {
		super.onDestroy()

		cancel()
	}
}