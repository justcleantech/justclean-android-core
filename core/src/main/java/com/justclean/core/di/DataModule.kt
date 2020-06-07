package com.justclean.core.di

import com.google.gson.Gson
import com.justclean.core.data.remote.AppSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module

var dataModule = module {
    single {
        AppSchedulerProvider()
    }

    factory {
        CompositeDisposable()
    }

    single {
        Gson()
    }
}