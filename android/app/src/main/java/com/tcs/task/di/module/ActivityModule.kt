package com.tcs.task.di.module


import com.tcs.task.views.feature.TaskActivity
import com.tcs.task.views.feature.TaskActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * ActivityModule provides android injection of it's module in activity.
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [TaskActivityModule::class])
    internal abstract fun bindBlogActivity(): TaskActivity
}