package com.marko.functional_marvel.herodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.marko.domain.entities.HeroId
import com.marko.functional_marvel.R
import com.marko.functional_marvel.base.BaseFragment
import com.marko.functional_marvel.entities.HeroDetailsParentEntity
import com.marko.functional_marvel.extensions.loadHero
import com.marko.presentation.entities.Comics
import com.marko.presentation.entities.Hero
import com.marko.presentation.entities.Series
import com.marko.presentation.herodetails.HeroDetailsViewModel
import com.marko.presentation.injection.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_hero_details.*
import javax.inject.Inject

class HeroDetailsFragment : BaseFragment() {

	@Inject
	lateinit var factory: ViewModelFactory

	private val viewModel: HeroDetailsViewModel by lazy(LazyThreadSafetyMode.NONE) {
		ViewModelProviders.of(this, factory).get(HeroDetailsViewModel::class.java)
	}

	private val heroId: HeroId by lazy(LazyThreadSafetyMode.NONE) {
		arguments !!.let {
			HeroDetailsFragmentArgs.fromBundle(it).heroId
		}
	}

	private val recyclerAdapter = HeroDetailsAdapter()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel.fetch(heroId = heroId)
		viewModel.hero.observe(this, Observer { handleHero(it) })
		viewModel.comics.observe(this, Observer { handleComics(it) })
		viewModel.series.observe(this, Observer { handleSeries(it) })
		viewModel.error.observe(this, Observer { it.printStackTrace() })
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? = inflater.inflate(R.layout.fragment_hero_details, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		heroDetailsRecyclerView.apply {
			adapter = recyclerAdapter
			layoutManager = LinearLayoutManager(context)
		}
	}

	private fun handleHero(hero: Hero) {
		heroDetailsImage.loadHero(hero.thumbnail.imageUrl)
		heroDetailsName.text = hero.name
		heroDetailsDescription.text = hero.description
	}

	private fun handleComics(comics: Comics) {
		recyclerAdapter.comics =
			HeroDetailsParentEntity(title = getString(R.string.comics), items = comics)
	}

	private fun handleSeries(series: List<Series>) {
		recyclerAdapter.series =
			HeroDetailsParentEntity(title = getString(R.string.series), items = series)
	}
}