package com.example.notificationsissue

import android.app.*
import androidx.test.core.app.*
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.*
import androidx.test.platform.app.*
import androidx.test.rule.*
import androidx.test.uiautomator.*
import androidx.test.uiautomator.By.*
import androidx.test.uiautomator.Until.*
import org.junit.*
import org.junit.runner.*

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun activityScenario() {
        ActivityScenario.launch(MainActivity::class.java)

        openAndClickNotification()
        onView(withText("Fragment B")).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        Espresso.pressBack()
        onView(withText("Fragment A")).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    private fun openAndClickNotification() {
        onView(withId(R.id.btn)).perform(click())

        val uiDevice = UiDevice.getInstance(instrumentation)

        uiDevice.openNotification()
        uiDevice.wait(findObject(text("Foo Title")), 3_000).let(::checkNotNull)
        uiDevice.wait(findObject(text("Bar Content")), 3_000).let(::checkNotNull)
        uiDevice.findObject(text("Foo Title")).click()
    }
}

val instrumentation: Instrumentation
    get() = InstrumentationRegistry.getInstrumentation()
