package com.tcs.task.rx

import io.reactivex.Scheduler

/**
 * Interface for application scheduler provider
 */
interface SchedulerProvider {
    fun ui(): Scheduler?
    fun computation(): Scheduler?
    fun io(): Scheduler?
}