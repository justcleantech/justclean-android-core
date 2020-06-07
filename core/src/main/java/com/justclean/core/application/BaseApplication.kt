package com.justclean.core.application

import android.app.Application
import com.justclean.core.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

/**
 * @name Hosam Almowaqee
 * Copyrights (c) 6/3/20 Created By Justclean Company
 */
abstract class BaseApplication : Application() {

    abstract val modules: List<Module>

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            //TODO: Here to add the shared module (scheduler provider, composite disposable... etc)
            modules(listOf(dataModule))
            //Here is the module which will over ride for any application class in any app
            modules(modules)
        }
    }

}