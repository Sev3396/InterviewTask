package com.tcs.task.di.component

import android.app.Application
import com.tcs.task.di.module.ActivityModule
import com.tcs.task.di.module.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Application component  enable selected modules to perform dependency injection
 * in application
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityModule::class, DataModule::class])
interface AppComponent : AndroidInjector<com.tcs.task.TaskApplication> {

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}