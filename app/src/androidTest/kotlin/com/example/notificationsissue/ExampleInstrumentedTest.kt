package com.example.notificationsissue

import android.app.Instrumentation
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By.text
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until.findObject
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun simpleTest() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withText("No data")).check(matches(isDisplayed()))

        onView(withId(R.id.btn)).perform(click())
        Espresso.pressBackUnconditionally()

        clickNotification()
        onView(withText("https://some.data1")).check(matches(isDisplayed()))

        onView(withId(R.id.btn)).perform(click())
        clickNotification()
        onView(withText("https://some.data2")).check(matches(isDisplayed()))
    }

    private fun clickNotification() {
        val uiDevice = UiDevice.getInstance(instrumentation)
        uiDevice.openNotification()
        uiDevice.wait(findObject(text("Foo Title")), 3_000).let(::checkNotNull)
        uiDevice.wait(findObject(text("Bar Content")), 3_000).let(::checkNotNull)
        uiDevice.findObject(text("Foo Title")).click()
        uiDevice.wait(findObject(text("NotificationsIssue")), 3_000)
    }
}

val instrumentation: Instrumentation
    get() = InstrumentationRegistry.getInstrumentation()
