package com.tcs.task

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.tcs.task.views.feature.TaskActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class StateListingTest {
    @get:Rule
    var rule = ActivityScenarioRule(TaskActivity::class.java)

    @Test
    fun testStateItemClick() {
        //test if data getting passed from listing fragment to details fragment.
    }
}