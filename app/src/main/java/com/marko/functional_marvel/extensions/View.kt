package com.marko.functional_marvel.extensions

import android.view.View

fun View.beVisibleIf(condition: Boolean) {
	visibility = if (condition) View.VISIBLE else View.GONE
}