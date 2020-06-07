package com.justclean.core.application

import com.justclean.core.di.viewModelModule
import org.koin.core.module.Module

/**
 * @name Hosam Almowaqee
 * Copyrights (c) 6/3/20 Created By Justclean Company
 */
class AppBaseApplication : BaseApplication() {

    override val modules: List<Module>
        get() = listOf(viewModelModule)

    override fun onCreate() {
        super.onCreate()
        //TODO: Complete the things which related to your project

    }
}