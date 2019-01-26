package com.marko.functional_marvel.heroes

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karumi.marvelapiclient.model.MarvelImage
import com.marko.functional_marvel.R
import com.marko.functional_marvel.entities.Hero
import com.marko.functional_marvel.entities.Heroes
import com.marko.functional_marvel.extensions.anim
import com.marko.functional_marvel.extensions.inflate
import com.marko.functional_marvel.extensions.loadHero
import kotlinx.android.synthetic.main.list_item_hero.view.*

class HeroesAdapter(
	private val onClick: (hero: Hero) -> Unit,
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
			portrait.setOnClickListener { onClick(hero) }

			favoriteButton.anim(hero.isFavorite)

			favoriteButton.setOnClickListener {
				favoriteButton.anim(! hero.isFavorite)
				onFavoriteClick(hero.copy(isFavorite = ! hero.isFavorite))
				submitList(heroes.map { if (it.id == hero.id) hero.copy(isFavorite = ! hero.isFavorite) else it })
			}

			portrait.loadHero(hero.thumbnail.getImageUrl(MarvelImage.Size.PORTRAIT_MEDIUM))

			name.text = hero.name

			description.text = hero.description
		}
	}
}

private object HeroesDiffer : DiffUtil.ItemCallback<Hero>() {
	override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean = oldItem.id == newItem.id

	override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean = oldItem == newItem
}