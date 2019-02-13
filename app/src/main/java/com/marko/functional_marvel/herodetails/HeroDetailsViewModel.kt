package com.marko.functional_marvel.herodetails

import com.marko.presentation.base.BaseViewModel
import javax.inject.Inject

class HeroDetailsViewModel @Inject constructor() : BaseViewModel() {

//	private val _comics = MutableLiveData<Comics>()
//	val comics: LiveData<Comics>
//		get() = _comics
//
//	private val _error = MutableLiveData<Throwable>()
//	val error: LiveData<Throwable>
//		get() = _error

	fun fetch() {}

//	fun fetch() = fetchComics().unsafeRunAsync {
//		it.fold(_error::postValue, _comics::postValue)
//	}
}