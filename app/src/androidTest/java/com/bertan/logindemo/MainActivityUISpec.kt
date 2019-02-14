package com.bertan.logindemo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

class MainActivityUISpec {
    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java, true, true)

    @Test
    fun when_create_activity_it_should_display_fields_and_button() {
        onView(withId(R.id.user)).check(matches(allOf(isDisplayed(), withText(""), withHint("User"))))
        onView(withId(R.id.password)).check(matches(allOf(isDisplayed(), withText(""), withHint("Password"))))
        onView(withId(R.id.login)).check(matches(isDisplayed()))
        onView(withId(R.id.authStatus)).check(matches(withText("")))
    }

    @Test
    fun when_successful_authentication_it_should_display_status_approved() {
        onView(withId(R.id.user)).perform(typeText("admin"), closeSoftKeyboard())
        onView(withId(R.id.password)).perform(typeText("p123"), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())

        onView(withId(R.id.authStatus)).check(matches(allOf(isDisplayed(), withText("Approved"))))
    }

    @Test
    fun when_rejected_authentication_it_should_display_status_rejected() {
        onView(withId(R.id.user)).perform(typeText("dummyUser"), closeSoftKeyboard())
        onView(withId(R.id.password)).perform(typeText("dummyPassword"), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())

        onView(withId(R.id.authStatus)).check(matches(allOf(isDisplayed(), withText("Rejected"))))
    }
}