package com.justclean.core.data.remote

import android.util.Log
import com.justclean.core.data.remote.SchedulerProvider.FlowableBackPressureStrategies
import com.justclean.core.data.remote.SchedulerProvider.FlowableBackPressureStrategies.*
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider : SchedulerProvider {

    fun testScheduler() {
        Log.e("TestKoin", "testScheduler: ")
    }

    override fun <T> ioToMainObservableScheduler(): ObservableTransformer<T, T> =
        ObservableTransformer { upstream ->
            upstream.subscribeOn(io())
                .observeOn(ui())
        }

    override fun <T> ioToMainSingleScheduler(): SingleTransformer<T, T> =
        SingleTransformer { upstream ->
            upstream.subscribeOn(io())
                .observeOn(ui())
        }


    override fun ioToMainCompletableScheduler(): CompletableTransformer =
        CompletableTransformer { upstream ->
            upstream.subscribeOn(io())
                .observeOn(ui())
        }

    override fun <T> ioToMainFlowableScheduler(
        strategy: FlowableBackPressureStrategies
    ): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            when (strategy) {
                BUFFER -> {
                    upstream.subscribeOn(io()).observeOn(ui()).onBackpressureBuffer()
                }
                LATEST -> {
                    upstream.subscribeOn(io()).observeOn(ui()).onBackpressureLatest()
                }
                DROP -> {
                    upstream.subscribeOn(io()).observeOn(ui()).onBackpressureDrop()
                }
                else -> {
                    upstream.subscribeOn(io()).observeOn(ui()).onBackpressureBuffer()
                }
            }
        }
    }


    override fun <T> ioToMainMaybeScheduler(): MaybeTransformer<T, T> =
        MaybeTransformer { upstream ->
            upstream.subscribeOn(io())
                .observeOn(ui())
        }


    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

}
