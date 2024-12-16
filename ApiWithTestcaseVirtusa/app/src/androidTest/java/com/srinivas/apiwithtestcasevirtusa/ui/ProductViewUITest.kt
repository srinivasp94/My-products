package com.srinivas.apiwithtestcasevirtusa.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.srinivas.apiwithtestcasevirtusa.R
import com.srinivas.apiwithtestcasevirtusa.di.DiAppModule
import com.srinivas.apiwithtestcasevirtusa.repository.FakeProductRepository
import com.srinivas.apiwithtestcasevirtusa.viewmodels.ProductViewModel
import com.srinivas.apiwithtestcasevirtusa.views.activity.MainActivity
import com.srinivas.apiwithtestcasevirtusa.views.adapter.ProductsAdapter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@UninstallModules(DiAppModule::class)
@HiltAndroidTest
class ProductViewUITest {

    private val testScope = CoroutineScope(Dispatchers.Default)

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var fakeRepository: FakeProductRepository
    private lateinit var viewModel: ProductViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        fakeRepository = FakeProductRepository()
        viewModel = ProductViewModel(fakeRepository)
        //viewModel.getProducts()
    }

    @Test
    fun test_progressbar_loading_for_RecyclerViewContents() {
        testScope.launch {
            // Wait for RecyclerView to load data
            //onView(withId(R.id.loader)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
            onView(withId(R.id.loader)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testRecyclerViewContainsItems(){
        testScope.launch {
            onView(withId(R.id.productList)).check(matches(isDisplayed())) // Check RecyclerView is displayed

            // Scroll to position 0 and check if item content is correct
            onView(withId(R.id.productList))
                .perform(RecyclerViewActions.scrollToPosition<ProductsAdapter.ProductViewHolder>(0))

            onView(withText("Test item")).check(matches(isDisplayed()))

            // Test click action on the first item
            onView(withId(R.id.productList))
                .perform(RecyclerViewActions.actionOnItemAtPosition<ProductsAdapter.ProductViewHolder>(
                        0,
                        click()
                    )
                )
        }
    }
    @Test
    fun testRecyclerViewWithEmptyList() {
        // If the RecyclerView is empty, check for the empty view message
        testScope.launch {
            onView(withId(R.id.text_NoProducts))
                .check(matches(isDisplayed()))
                .check(matches(withText("No items available")))
        }
    }
}