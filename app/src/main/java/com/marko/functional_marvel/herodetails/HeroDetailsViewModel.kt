package com.marko.functional_marvel.herodetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.effects.unsafeRunAsync
import com.marko.functional_marvel.base.BaseViewModel
import com.marko.functional_marvel.entities.Comics
import com.marko.functional_marvel.injection.HKImplementation
import com.marko.functional_marvel.usecases.FetchComics
import com.marko.functional_marvel.usecases.invoke
import javax.inject.Inject

class HeroDetailsViewModel @Inject constructor(
	private val fetchComics: FetchComics<HKImplementation>
) : BaseViewModel() {

	private val _comics = MutableLiveData<Comics>()
	val comics: LiveData<Comics>
		get() = _comics

	private val _error = MutableLiveData<Throwable>()
	val error: LiveData<Throwable>
		get() = _error

	fun fetch() = fetchComics().unsafeRunAsync {
		it.fold(_error::postValue, _comics::postValue)
	}
}