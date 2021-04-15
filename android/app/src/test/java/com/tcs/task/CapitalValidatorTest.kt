package com.tcs.task

import com.tcs.task.data.manager.DataManager
import com.tcs.task.rx.SchedulerProvider
import com.tcs.task.views.feature.TaskViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito


@RunWith(JUnit4::class)
class CapitalValidatorTest {

    lateinit var taskViewModel: TaskViewModel
    lateinit var dataManager: DataManager
    lateinit var schedulerProvider: SchedulerProvider


    @Before
    fun setUp() {
        dataManager = Mockito.mock(DataManager::class.java)
        schedulerProvider = Mockito.mock(SchedulerProvider::class.java)
        taskViewModel = TaskViewModel(dataManager, schedulerProvider)
    }
    @Test
    fun fileAvailable_withJsonData_ReturnsTrue() {
        taskViewModel.getStateData()
    }
}