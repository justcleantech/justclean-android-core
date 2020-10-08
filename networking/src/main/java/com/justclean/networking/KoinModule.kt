package com.justclean.networking

import org.koin.dsl.module

val dataManagerModule = module {
    single {
        ServiceBuilder.buildService()
    }
}