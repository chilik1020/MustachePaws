package com.chilik1020.mustachepaws.ui.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

import com.chilik1020.mustachepaws.R
import com.chilik1020.mustachepaws.rules.ActivityTestRule


import org.junit.Rule
import org.junit.jupiter.api.Test


internal class MainActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun checkContainerIsDisplayed() {
        onView(ViewMatchers.withId(R.id.fragment_container))
            .check(matches(isDisplayed()))
    }
}