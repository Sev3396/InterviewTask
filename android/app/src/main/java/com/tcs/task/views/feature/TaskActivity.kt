package com.tcs.task.views.feature

import android.os.Bundle
import com.tcs.task.R
import com.tcs.task.base.BaseActivity
import javax.inject.Inject

class TaskActivity: BaseActivity<TaskViewModel>() {

    @Inject
    lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override val viewModel: TaskViewModel
        get() = taskViewModel
}
