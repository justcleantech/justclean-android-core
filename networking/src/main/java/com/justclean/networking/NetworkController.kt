package com.justclean.networking

import com.justclean.networking.RequestType.*
import org.koin.core.KoinComponent
import org.koin.core.inject

object NetworkController: KoinComponent {

    private val request: APIService by inject()

    fun processRequest(type: RequestType, fullUrl: String) =
        when (type) {
            is GET -> request.getRequest(fullUrl, type.queries)
            is POST -> request.postRequest(fullUrl, type.body)
            is PUT -> request.putRequest(fullUrl, type.body)
            is PATCH -> request.patchRequest(fullUrl, type.body)
            is DELETE -> request.deleteRequest(fullUrl)
        }
}