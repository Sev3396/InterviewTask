package com.tcs.testapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tcs.testapp.model.State
import com.tcs.testapp.model.StateCapitalDO
import com.tcs.testapp.model.StateCapitalService
import com.tcs.testapp.viewmodel.StateCapitalsViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class StateCapitalViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var stateCapitalService: StateCapitalService

    @InjectMocks
    var viewModel = StateCapitalsViewModel()

    private var testData: Single<StateCapitalDO>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }


    @Before
    fun setUpRxSchedulers() {
        val schedulerFake = object: Scheduler() {
            override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { schedulerFake }
        RxJavaPlugins.setInitComputationSchedulerHandler { schedulerFake }
        RxJavaPlugins.setInitNewThreadSchedulerHandler{ schedulerFake }
        RxJavaPlugins.setInitSingleSchedulerHandler { schedulerFake }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { schedulerFake }
    }

    @Test
    fun getStateCapitalsSuccess() {
        val stateDO = State("India","No",1,"/data/hello.png","Test")
        val stateCapitalFake = StateCapitalDO("https://myapp.tcs.com", arrayListOf(stateDO))

        testData = Single.just(stateCapitalFake)

        `when`(stateCapitalService.getStateCapitalsApi()).thenReturn(testData)

        viewModel.getStateCapitals()

        Assert.assertEquals(1, viewModel.stateCapitals.value?.state?.size)
        Assert.assertEquals(false, viewModel.loadError.value)
        Assert.assertEquals(false, viewModel.loading.value)
    }


    @Test
    fun getCountriesError() {
        testData = Single.error(Throwable())

        `when`(stateCapitalService.getStateCapitalsApi()).thenReturn(testData)

        viewModel.getStateCapitals()

        Assert.assertEquals(true, viewModel.loadError.value)
        Assert.assertEquals(false, viewModel.loading.value)
    }
}