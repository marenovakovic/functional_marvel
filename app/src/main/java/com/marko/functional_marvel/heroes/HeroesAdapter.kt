package com.marko.functional_marvel.heroes

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marko.domain.entities.HeroId
import com.marko.functional_marvel.R
import com.marko.functional_marvel.extensions.anim
import com.marko.functional_marvel.extensions.inflate
import com.marko.functional_marvel.extensions.loadHero
import com.marko.presentation.entities.Hero
import com.marko.presentation.entities.Heroes
import kotlinx.android.synthetic.main.list_item_hero.view.*

class HeroesAdapter(
	private val onClick: (heroId: HeroId) -> Unit,
	private val onFavoriteClick: (hero: Hero) -> Unit
) : ListAdapter<Hero, HeroesAdapter.HeroHolder>(HeroesDiffer) {

	var heroes: Heroes = mutableListOf()
		set(value) {
			field = value
			submitList(value)
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder {
		val view = parent.inflate(R.layout.list_item_hero)
		return HeroHolder(view)
	}

	override fun onBindViewHolder(holder: HeroHolder, position: Int) =
		holder.bind(getItem(position))

	inner class HeroHolder(view: View) : RecyclerView.ViewHolder(view) {

		private val portrait = itemView.listItemHeroImage
		private val name = itemView.listItemHeroName
		private val favoriteButton = itemView.listItemHeroFavoriteButton
		private val description = itemView.listItemHeroDescription

		fun bind(hero: Hero) {
			var isFavorite = hero.isFavorite

			portrait.setOnClickListener { onClick(hero.id) }

			favoriteButton.anim(hero.isFavorite)

			favoriteButton.setOnClickListener {
				isFavorite = ! isFavorite
				favoriteButton.anim(isFavorite)
				onFavoriteClick(hero.copy(isFavorite = isFavorite))
			}

			portrait.loadHero(hero.thumbnail.imageUrl)

			name.text = hero.name

			description.text = hero.description
		}
	}
}

private object HeroesDiffer : DiffUtil.ItemCallback<Hero>() {
	override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean = oldItem.id == newItem.id

	override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean = oldItem == newItem
}