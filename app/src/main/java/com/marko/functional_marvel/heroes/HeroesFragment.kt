package com.marko.functional_marvel.heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marko.domain.entities.HeroId
import com.marko.functional_marvel.R
import com.marko.functional_marvel.base.BaseFragment
import com.marko.functional_marvel.extensions.beVisibleIf
import com.marko.functional_marvel.injection.viewmodel.ViewModelFactory
import com.marko.presentation.entities.Hero
import com.marko.presentation.heroes.HeroesViewModel
import kotlinx.android.synthetic.main.fragment_heroes.*
import javax.inject.Inject

/**
 * [BaseFragment] displaying [Heroes] list
 */
class HeroesFragment : BaseFragment() {

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
		viewModel.loading.observe(this, Observer { heroesProgressBar.beVisibleIf(it) })
		viewModel.heroes.observe(this, Observer { heroesAdapter.heroes = it })
		viewModel.error.observe(this, Observer { it.printStackTrace() })
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? = inflater.inflate(R.layout.fragment_heroes, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		heroesRecyclerView.apply {
			adapter = heroesAdapter
			layoutManager = LinearLayoutManager(context).apply { isItemPrefetchEnabled = true }
			setHasFixedSize(true)
		}
	}

	private fun showHeroDetails(heroId: HeroId) {
		val action = HeroesFragmentDirections.actionHeroesFragmentToHeroDetailsFragment(heroId)
		findNavController().navigate(action)
	}

	private fun setFavorite(hero: Hero) = viewModel.setFavorite(hero)
}
