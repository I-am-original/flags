package com.raccoon.flags

import android.content.res.Resources
import android.os.SystemClock
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.Toolbar
import android.view.View
import com.raccoon.flags.adapter.GridAdapter
import com.raccoon.flags.view.DetailActivity
import com.raccoon.flags.view.GridActivity
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    private val activityTestRule = ActivityTestRule(GridActivity::class.java)
    private lateinit var resources: Resources

    @Before
    fun setUp() {
        activityTestRule.launchActivity(null)
        resources = activityTestRule.activity.resources
        Intents.init()
    }

    @After
    fun release() {
        Intents.release()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.raccoon.flags", appContext.packageName)
    }

    @Test
    fun checkActivityTitle() {
        onView(withId(R.id.toolbar)).check({ view, _ ->
            run {
                view as Toolbar
                view.title == resources.getString(R.string.app_name)
            }
        })
    }

    @Test
    fun openDetailActivity() {
        SystemClock.sleep(3000)
        onView(withId(R.id.recyclerViewGrid))
                .perform(RecyclerViewActions.actionOnItemAtPosition<GridAdapter.ItemViewHolder>(0, CustomViewAction.clickChildViewWithId(R.id.card_view)))
        Intents.intended(hasComponent(DetailActivity::class.java.canonicalName))
    }
}

object CustomViewAction {
    fun clickChildViewWithId(id: Int) =
            object : ViewAction {
                override fun getDescription(): String = "Click on view with ID"

                override fun getConstraints(): Matcher<View>? = null

                override fun perform(uiController: UiController, view: View) {
                    view.findViewById<View>(id).performClick()
                }

            }
}