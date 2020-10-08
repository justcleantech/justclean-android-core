package com.justclean.networking

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Mario Gamal on 10/8/20
 * Copyright © 2020 Jusclean. All rights reserved.
 */
object ServiceBuilder {

    fun buildService(interceptors: List<Interceptor>, debugTimeOut: Long, releaseTimeOut: Long): APIService {
        val client = OkHttpClient.Builder()

        interceptors.forEach {
            client.addInterceptor(it)
        }

        client.connectTimeout(if (BuildConfig.DEBUG) debugTimeOut else releaseTimeOut, TimeUnit.SECONDS)
            .readTimeout(if (BuildConfig.DEBUG) debugTimeOut else releaseTimeOut, TimeUnit.SECONDS)
            .writeTimeout(if (BuildConfig.DEBUG) debugTimeOut else releaseTimeOut, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder()
            .client(client.build())
            .baseUrl("https://google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

        return retrofit.create(APIService::class.java)
    }
}