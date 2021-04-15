package com.tcs.testapp.di

import com.tcs.testapp.model.StateCapitalApi
import com.tcs.testapp.model.StateCapitalService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class ApiModule {

    private val BASE_URL = "https://raw.githubusercontent.com"///blob/main/states.json

    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun stateCapitalApi(): StateCapitalApi = retrofit.create(StateCapitalApi::class.java)

    @Provides
    fun stateCapitalService(): StateCapitalService = StateCapitalService()
}