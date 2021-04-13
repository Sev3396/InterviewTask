package com.tcs.task.base

import androidx.lifecycle.ViewModel
import com.tcs.task.data.manager.DataManager
import com.tcs.task.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Base View Model for all View model which attached to activity
 */
open class BaseViewModel<N>(
    val dataManager: DataManager,
    val schedulerProvider: SchedulerProvider
) : ViewModel() {
    open var navigator: N? = null
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}