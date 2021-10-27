package com.example.holybibleapp

import android.content.Context
import android.content.SharedPreferences
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
abstract class BaseTest {

    @get: Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)
    private lateinit var sharedPreferences: SharedPreferences


    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        sharedPreferences =
            appContext.getSharedPreferences("MockCollapsedItemsIdList", Context.MODE_PRIVATE)
        clear()
    }

    @After
    fun clear() {
        sharedPreferences.edit().putStringSet("MockCollapsedItemsIdsKey", emptySet()).apply()
    }

    protected fun String.checkVisible() {
        onView(withText(this))
            .check(matches(isDisplayed()))
    }

    protected fun String.tap() {
        onView(withText(this)).perform(click())
    }

    protected fun String.checkDoesntExist() {
        onView(withText(this)).check(doesNotExist())
    }
}