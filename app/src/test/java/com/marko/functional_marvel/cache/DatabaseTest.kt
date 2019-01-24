package com.marko.functional_marvel.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
abstract class DatabaseTest {

	@Rule
	@JvmField
	val instantTaskExecutorRule = InstantTaskExecutorRule()

	private val context = InstrumentationRegistry.getInstrumentation().context

	private lateinit var database: MarvelDatabase
	protected val heroesDao: HeroesDao
		get() = database.heroesDao()

	@Before
	fun open() {
		database = Room
			.inMemoryDatabaseBuilder(context, MarvelDatabase::class.java)
			.allowMainThreadQueries()
			.build()
	}

	@After
	fun close() {
		database.close()
	}
}