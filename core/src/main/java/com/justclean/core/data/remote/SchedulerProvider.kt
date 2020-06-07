package com.justclean.core.data.remote

import io.reactivex.*

interface SchedulerProvider {

    fun ui(): Scheduler

    fun computation(): Scheduler

    fun io(): Scheduler

    fun <T> ioToMainObservableScheduler(): ObservableTransformer<T, T>

    fun <T> ioToMainSingleScheduler(): SingleTransformer<T, T>

    fun ioToMainCompletableScheduler(): CompletableTransformer

    fun <T> ioToMainFlowableScheduler(strategy: FlowableBackPressureStrategies? = null): FlowableTransformer<T, T>

    fun <T> ioToMainMaybeScheduler(): MaybeTransformer<T, T>


    enum class FlowableBackPressureStrategies {
        //the source will buffer all the events until the subscriber can consume them:
        BUFFER,
        // .LATEST will overwrite elements that our subscriber can't handle and keep only the latest ones, hence the name.
        LATEST,
        //to discard the events that cannot be consumed instead of buffering them.
        DROP,
    }

}