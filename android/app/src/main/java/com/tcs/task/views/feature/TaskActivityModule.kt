package com.tcs.task.views.feature

import com.tcs.task.data.manager.DataManager
import com.tcs.task.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class TaskActivityModule {
    @Provides
    fun provideTaskViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider)
            : TaskViewModel {
        return TaskViewModel(dataManager, schedulerProvider)
    }
}