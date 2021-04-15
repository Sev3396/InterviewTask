package com.tcs.testapp.model

import com.tcs.testapp.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class StateCapitalService {

    @Inject
    lateinit var api: StateCapitalApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getStateCapitalsApi(): Single<StateCapitalDO> = api.getStateCapitalsData()
}