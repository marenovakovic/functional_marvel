package com.marko.functional_marvel.home

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.marko.functional_marvel.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

abstract class HomeDrawerActivity : AppCompatActivity(),
	NavigationView.OnNavigationItemSelectedListener {

	override fun onStart() {
		setSupportActionBar(toolbar)

		val toggle = ActionBarDrawerToggle(
			this,
			drawerLayout,
			toolbar,
			R.string.navigation_drawer_open,
			R.string.navigation_drawer_close
		)
		drawerLayout.addDrawerListener(toggle)
		toggle.syncState()

		navView.setNavigationItemSelectedListener(this)

		super.onStart()
	}

	override fun onBackPressed() =
		if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
			drawerLayout.closeDrawer(GravityCompat.START)
		} else {
			super.onBackPressed()
		}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.main, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean =
		when (item.itemId) {
			R.id.action_settings -> true
			else -> super.onOptionsItemSelected(item)
		}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.nav_camera -> Unit
			R.id.nav_gallery -> Unit
			R.id.nav_slideshow -> Unit
			R.id.nav_manage -> Unit
			R.id.nav_share -> Unit
			R.id.nav_send -> Unit
		}

		drawerLayout.closeDrawer(GravityCompat.START)
		return true
	}
}