package com.marko.functional_marvel.herodetails

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marko.functional_marvel.R
import com.marko.functional_marvel.extensions.inflate
import com.marko.functional_marvel.extensions.loadHero
import com.marko.presentation.entities.MarvelItem
import kotlinx.android.synthetic.main.generic_list_item_hero_details.view.*

class HeroDetailsGenericAdapter :
	ListAdapter<MarvelItem, HeroDetailsGenericAdapter.MarvelItemViewHolder>(MarvelItemDiffer) {

	var items: List<MarvelItem> = emptyList()
		set(value) = submitList(value)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelItemViewHolder {
		val view = parent.inflate(R.layout.generic_list_item_hero_details)
		return MarvelItemViewHolder(view)
	}

	override fun onBindViewHolder(holder: MarvelItemViewHolder, position: Int) =
		holder.bind(getItem(position))

	inner class MarvelItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		fun bind(marvelItem: MarvelItem) {
			itemView.genericName.text = marvelItem.itemTitle
			itemView.genericImage.loadHero(marvelItem.imageUrl)
		}
	}
}

private object MarvelItemDiffer : DiffUtil.ItemCallback<MarvelItem>() {
	override fun areItemsTheSame(oldItem: MarvelItem, newItem: MarvelItem): Boolean =
		oldItem.itemId == newItem.itemId

	override fun areContentsTheSame(oldItem: MarvelItem, newItem: MarvelItem): Boolean =
		oldItem == newItem
}