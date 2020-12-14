package com.justclean.networking

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.justclean.networking.RequestType.*
import io.reactivex.Flowable
import io.reactivex.functions.Function
import okhttp3.Interceptor

object NetworkController {

    var request: APIService? = null

    fun init(interceptors: List<Interceptor> = listOf(), debugTimeOut: Long = 30, releaseTimeOut: Long = 10) {
        request = ServiceBuilder.buildService(interceptors, debugTimeOut, releaseTimeOut)
    }

    inline fun <reified T> processRequest(type: RequestType, fullUrl: String): Flowable<T> {
        if (request == null)
            throw NullPointerException("Please call NetworkController.init() in your Application class")

        val response = when (type) {
            is GET -> request!!.getRequest(fullUrl, type.queries)
            is POST -> request!!.postRequest(fullUrl, type.body)
            is PUT -> request!!.putRequest(fullUrl, type.body)
            is PATCH -> request!!.patchRequest(fullUrl, type.body)
            is DELETE -> request!!.deleteRequest(fullUrl)
        }

        return response.map(object : Function<JsonElement, T> {
            override fun apply(t: JsonElement): T {
                return Gson().fromJson(t, object: TypeToken<T>(){}.type)
            }
        })
    }

}