package com.tcs.task.views.feature

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentProvider {
    @ContributesAndroidInjector(modules = [TaskActivityModule::class])
    abstract fun bindSourcesFragment(): StateListingFragment

    @ContributesAndroidInjector(modules = [TaskActivityModule::class])
    abstract fun bindAllNewsFragment(): StateDetailsFragment
}