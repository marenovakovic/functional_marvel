package com.marko.functional_marvel.extensions

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.marko.functional_marvel.R

fun ImageView.anim(fromInitState: Boolean) {
	if (fromInitState) startAnimation() else reverseAnimation()
}

private fun ImageView.startAnimation() {
	val stateSet = intArrayOf(android.R.attr.state_checked * 1)
	setImageState(stateSet, true)
}

private fun ImageView.reverseAnimation() {
	val stateSet = intArrayOf(android.R.attr.state_checked * - 1)
	setImageState(stateSet, true)
}

private const val IMAGE_NOT_AVAILABLE_URL =
	"http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available"

private val ImageView.circularProgressDrawable: CircularProgressDrawable
	get() = CircularProgressDrawable(context).apply {
		strokeWidth = 2.dp.toFloat()
		centerRadius = 16.dp.toFloat()
		this.setTintList(ColorStateList.valueOf(context.getColor(R.color.colorAccent)))
		start()
	}

fun ImageView.loadHero(url: String) {
	val progressDrawable = circularProgressDrawable

	if (url.contains(IMAGE_NOT_AVAILABLE_URL)) {
		Glide.with(this)
			.load(R.drawable.ic_iron_man)
			.apply(RequestOptions.placeholderOf(progressDrawable))
			.apply(RequestOptions.centerCropTransform())
			.into(this)
			.let { }
	} else {
		Glide.with(this)
			.load(url)
			.apply(RequestOptions.placeholderOf(progressDrawable))
			.apply(RequestOptions.centerCropTransform())
			.into(this)
			.let { }
	}
}