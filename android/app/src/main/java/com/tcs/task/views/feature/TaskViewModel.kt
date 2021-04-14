package com.tcs.task.views.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.tcs.task.base.BaseViewModel
import com.tcs.task.data.manager.DataManager
import com.tcs.task.model.State
import com.tcs.task.model.StateResponse
import com.tcs.task.rx.SchedulerProvider
import java.io.IOException

class TaskViewModel(dataManager: DataManager, scheduleProvider: SchedulerProvider):
    BaseViewModel<TaskNavigator>(dataManager, scheduleProvider) {

    private val mStateList = MutableLiveData<List<State>>()
    private lateinit var domainURL: String

    var taskNavigator: TaskNavigator?
        get() = super.navigator
        set(navigator) {
            super.navigator = navigator
        }

    override fun onCleared() {
        super.onCleared()
    }

    fun getStateList() {
        val stateResponse: StateResponse = Gson().fromJson(getJsonData(), StateResponse::class.java)
        domainURL = stateResponse.domain
        for(state in stateResponse.state) {
            state.imageUrl = domainURL + state.imageUrl
        }
        mStateList.value = stateResponse.state
    }

    fun getStateData(): LiveData<List<State>> {
        return mStateList
    }

    private fun getJsonData(): String {
        return try {
            dataManager.context.assets.open("states.json").bufferedReader().use {
                it.readText()
            }
        } catch(e: IOException) {
            e.printStackTrace()
            ""
        }
    }
}