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
import kotlinx.android.synthetic.main.grid_item_hero.view.*

class HeroesAdapter(
	private val onClick: (hero: Hero) -> Unit,
	private val onFavoriteClick: (hero: Hero) -> Unit
) : ListAdapter<Hero, HeroesAdapter.HeroHolder>(HeroesDiffer) {

	var heroes: Heroes
		set(value) = submitList(value)
		get() = listOf()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder {
		val view = parent.inflate(R.layout.grid_item_hero)
		return HeroHolder(view)
	}

	override fun onBindViewHolder(holder: HeroHolder, position: Int) =
		holder.bind(getItem(position))

	inner class HeroHolder(view: View) : RecyclerView.ViewHolder(view) {

		private val portrait = itemView.gridItemHeroImage
		private val name = itemView.gridItemHeroName
		private val favoriteButton = itemView.gridItemHeroFavoriteButton

		fun bind(hero: Hero) {
			portrait.setOnClickListener { onClick(hero) }
			favoriteButton.setOnClickListener {
				favoriteButton.anim(true)
				onFavoriteClick(hero)
			}
			portrait.loadHero(hero.thumbnail.getImageUrl(MarvelImage.Size.PORTRAIT_MEDIUM))
			name.text = hero.name
		}
	}
}

private object HeroesDiffer : DiffUtil.ItemCallback<Hero>() {
	override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean = oldItem.id == newItem.id

	override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean = oldItem == newItem
}