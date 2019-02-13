package com.marko.functional_marvel.herodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marko.functional_marvel.R

class HeroDetailsFragment : Fragment() {

//	private val hero: Hero by lazy(LazyThreadSafetyMode.NONE) {
//		arguments !!.let {
//			HeroDetailsFragmentArgs.fromBundle(it)
//				.hero
//		}
//	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? = inflater.inflate(R.layout.fragment_hero_details, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

//		heroDetailsImage.loadHero(hero.thumbnail.getImageUrl(MarvelImage.Size.PORTRAIT_FANTASTIC))
//		heroDetailsName.text = hero.name
//		heroDetailsDescription.text = hero.description
	}
}