package com.tcs.task.data.manager

import android.content.Context
import javax.inject.Inject

/**
 * AppDataManager is implementation of [DataManager]
 */
class AppDataManager @Inject
constructor(
    override val context: Context,
) : DataManager