package com.marko.functional_marvel.extensions

import android.content.res.Resources

val Int.dp: Int
	get() = this.toDp()

private fun Int.toDp() = (Resources.getSystem().displayMetrics.density * this).toInt()