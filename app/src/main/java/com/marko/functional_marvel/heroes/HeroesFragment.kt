package com.marko.functional_marvel.heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.marko.functional_marvel.R
import com.marko.functional_marvel.base.BaseFragment
import com.marko.functional_marvel.entities.Hero
import com.marko.functional_marvel.injection.viewmodel.ViewModelFactory
import com.marko.functional_marvel.presentation.HeroesViewModel
import kotlinx.android.synthetic.main.fragment_heroes.*
import javax.inject.Inject

class HeroesFragment : BaseFragment() {

	companion object {
		private const val GRID_SPAN = 3
	}

	@Inject
	lateinit var factory: ViewModelFactory

	private val viewModel: HeroesViewModel by lazy(LazyThreadSafetyMode.NONE) {
		ViewModelProviders.of(this, factory).get(HeroesViewModel::class.java)
	}

	private val heroesAdapter: HeroesAdapter by lazy(LazyThreadSafetyMode.NONE) {
		HeroesAdapter(onClick = ::showHeroDetails, onFavoriteClick = ::setFavorite)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel.fetch()
		viewModel.heroes.observe(this, Observer { heroesAdapter.heroes = it })
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? = inflater.inflate(R.layout.fragment_heroes, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		heroesRecyclerView.apply {
			adapter = heroesAdapter
			layoutManager =
				StaggeredGridLayoutManager(GRID_SPAN, StaggeredGridLayoutManager.VERTICAL)
					.apply { isItemPrefetchEnabled = true }
		}
	}

	private fun showHeroDetails(hero: Hero) {
		val action = HeroesFragmentDirections.actionHeroesFragmentToHeroDetailsFragment(hero)
		findNavController().navigate(action)
	}

	private fun setFavorite(hero: Hero) = viewModel.saveHero(hero)
}
