package com.justclean.networking

sealed class RequestType {
    data class GET(
        val queries: HashMap<String, Any> = hashMapOf(),
        val headers: HashMap<String, Any> = hashMapOf()
    ) : RequestType()

    data class POST(
        val body: RequestBody = EmptyBody(),
        val headers: HashMap<String, Any> = hashMapOf()
    ) : RequestType()

    data class PUT(
        val body: RequestBody = EmptyBody(),
        val headers: HashMap<String, Any> = hashMapOf()
    ) : RequestType()

    data class PATCH(
        val body: RequestBody = EmptyBody(),
        val headers: HashMap<String, Any> = hashMapOf()
    ) : RequestType()

    object DELETE : RequestType()
}