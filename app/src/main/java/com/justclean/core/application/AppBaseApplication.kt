package com.justclean.core.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.justclean.core.di.viewModelModule
import com.justclean.networking.NetworkController
import org.koin.core.context.startKoin
import org.koin.core.module.Module

/**
 * @name Hosam Almowaqee
 * Copyrights (c) 6/3/20 Created By Justclean Company
 */
class AppBaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        //TODO: Complete the things which related to your project
        NetworkController.init()
        startKoin {
            modules(listOf(viewModelModule))
        }
    }
}