package com.tcs.task

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TaskApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return com.tcs.task.di.component.DaggerAppComponent.builder().application(this).build()
    }
}