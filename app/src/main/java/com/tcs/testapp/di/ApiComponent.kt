package com.tcs.testapp.di

import com.tcs.testapp.model.StateCapitalService
import com.tcs.testapp.viewmodel.StateCapitalsViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: StateCapitalService)
    fun inject(viewModel: StateCapitalsViewModel)
}