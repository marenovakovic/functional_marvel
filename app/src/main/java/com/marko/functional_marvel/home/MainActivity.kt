package com.marko.functional_marvel.home

import android.os.Bundle
import com.marko.functional_marvel.R

class MainActivity : HomeDrawerActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}
}