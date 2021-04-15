package com.tcs.testapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tcs.testapp.di.DaggerApiComponent
import com.tcs.testapp.model.StateCapitalService
import com.tcs.testapp.model.StateCapitalDO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class StateCapitalsViewModel : ViewModel() {

    val stateCapitals by lazy { MutableLiveData<StateCapitalDO>()}
    val loadError by lazy { MutableLiveData<Boolean>()}
    val loading by lazy { MutableLiveData<Boolean>() }

    private val disposable = CompositeDisposable()

    @Inject
    lateinit var service: StateCapitalService

    init {
        DaggerApiComponent.create().inject(this)
    }

    /**
     * This method is used to start the Api call and initialize observers
     */
    fun getStateCapitals() {
        loading.value = true
        loadError.value = false

        disposable.add(
            service.getStateCapitalsApi()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<StateCapitalDO>() {
                    override fun onSuccess(stateCapitalDO: StateCapitalDO) {
                        loadError.value = false
                        stateCapitals.value = stateCapitalDO
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadError.value = true
                        stateCapitals.value = null
                    }

                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}