package com.marko.functional_marvel.injection.application

import com.marko.functional_marvel.app.App
import com.marko.functional_marvel.app.AppModule
import com.marko.functional_marvel.injection.activity.ActivityBindingModule
import com.marko.functional_marvel.injection.fragment.FragmentBindingModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		AndroidInjectionModule::class,
		AndroidSupportInjectionModule::class,
		AppModule::class,
		ActivityBindingModule::class,
		FragmentBindingModule::class
	]
)
interface ApplicationComponent : AndroidInjector<App>

val App.applicationComponent: ApplicationComponent
	get() =
		DaggerApplicationComponent
			.builder()
			.appModule(AppModule(this))
			.build()