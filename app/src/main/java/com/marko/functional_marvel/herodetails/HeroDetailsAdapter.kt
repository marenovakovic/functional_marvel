package com.marko.functional_marvel.herodetails

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marko.functional_marvel.R
import com.marko.functional_marvel.entities.HeroDetailsParentEntity
import com.marko.functional_marvel.extensions.inflate
import kotlinx.android.synthetic.main.list_item_hero_details.view.*

class HeroDetailsAdapter :
	ListAdapter<HeroDetailsParentEntity, HeroDetailsAdapter.HeroDetailsViewHolder>(
		HeroDetailsParentEntityDiffer
	) {

	private val viewPool = RecyclerView.RecycledViewPool()

	var comics: HeroDetailsParentEntity = HeroDetailsParentEntity(title = "", items = emptyList())
		set(value) {
			field = value
			buildList()
		}

	var series: HeroDetailsParentEntity = HeroDetailsParentEntity(title = "", items = emptyList())
		set(value) {
			field = value
			buildList()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroDetailsViewHolder {
		val view = parent.inflate(R.layout.list_item_hero_details)
		return HeroDetailsViewHolder(view)
	}

	override fun onBindViewHolder(holder: HeroDetailsViewHolder, position: Int) =
		holder.bind(getItem(position))

	private fun buildList() {
		val list = mutableListOf<HeroDetailsParentEntity>()

		if (comics.items.isNotEmpty()) list += comics
		if (series.items.isNotEmpty()) list += series

		submitList(list)
	}

	inner class HeroDetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		fun bind(heroDetailsParentEntity: HeroDetailsParentEntity) {
			itemView.listItemHeroDetailsTitle.text = heroDetailsParentEntity.title

			val recyclerAdapter = HeroDetailsGenericAdapter()
			recyclerAdapter.items = heroDetailsParentEntity.items

			itemView.listItemHeroDetailsRecyclerView.apply {
				adapter = recyclerAdapter
				layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
				setRecycledViewPool(viewPool)
			}
		}
	}
}

private object HeroDetailsParentEntityDiffer :
	DiffUtil.ItemCallback<HeroDetailsParentEntity>() {
	override fun areItemsTheSame(
		oldItem: HeroDetailsParentEntity,
		newItem: HeroDetailsParentEntity
	): Boolean = oldItem.title == newItem.title

	override fun areContentsTheSame(
		oldItem: HeroDetailsParentEntity,
		newItem: HeroDetailsParentEntity
	): Boolean = oldItem == newItem
}