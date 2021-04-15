package com.tcs.testapp.model

import io.reactivex.Single
import retrofit2.http.*

interface StateCapitalApi {

    @GET("/Sev3396/InterviewTask/main/states.json")
    fun getStateCapitalsData(): Single<StateCapitalDO>

}