package com.justclean.networking

import com.google.gson.JsonElement
import com.justclean.networking.RequestType.*
import io.reactivex.Flowable
import okhttp3.Interceptor

object NetworkController {

    private lateinit var request: APIService

    fun init(interceptors: List<Interceptor> = listOf(), debugTimeOut: Long = 30, releaseTimeOut: Long = 10) {
        request = ServiceBuilder.buildService(interceptors, debugTimeOut, releaseTimeOut)
    }

    fun processRequest(type: RequestType, fullUrl: String): Flowable<JsonElement> {
        if (!::request.isInitialized)
            throw NullPointerException("Please call NetworkController.init() in your Application class")

        return when (type) {
            is GET -> request.getRequest(fullUrl, type.queries)
            is POST -> request.postRequest(fullUrl, type.body)
            is PUT -> request.putRequest(fullUrl, type.body)
            is PATCH -> request.patchRequest(fullUrl, type.body)
            is DELETE -> request.deleteRequest(fullUrl)
        }
    }
}