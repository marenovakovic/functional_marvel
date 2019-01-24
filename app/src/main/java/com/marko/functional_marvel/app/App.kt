package com.marko.functional_marvel.app

import com.marko.functional_marvel.injection.application.applicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {
	private val component = applicationComponent

	override fun applicationInjector(): AndroidInjector<out DaggerApplication> = component
}