package com.justclean.sample.ui.viewmodels

import android.util.Log
import com.justclean.core.base.BaseViewModel
import com.justclean.core.data.remote.Model
import org.koin.core.KoinComponent

class SampleViewModel : BaseViewModel(), KoinComponent {

    val test = "Hello ViewModel"

    init {
        helloKoin()
//        schedulerProvider.testScheduler()
        Log.d("TestKoin", test)
    }

    override fun handleError(error: Model<*>) {
        //
    }
}