package com.justclean.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Mario Gamal on 10/8/20
 * Copyright © 2020 Jusclean. All rights reserved.
 */
object ServiceBuilder {
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    
    private val retrofit = Retrofit.Builder()
        .client(client.build())
        .baseUrl("https://google.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

    fun buildService(): APIService = retrofit.create(APIService::class.java)
}