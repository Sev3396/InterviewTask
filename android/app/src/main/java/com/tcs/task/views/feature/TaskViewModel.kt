package com.tcs.task.views.feature

import com.tcs.task.base.BaseViewModel
import com.tcs.task.data.manager.DataManager
import com.tcs.task.rx.SchedulerProvider

class TaskViewModel(dataManager: DataManager, scheduleProvider: SchedulerProvider):
    BaseViewModel<TaskNavigator>(dataManager, scheduleProvider) {

    var blogNavigator: TaskNavigator?
        get() = super.navigator
        set(navigator) {
            super.navigator = navigator
        }

    override fun onCleared() {
        super.onCleared()
    }
}