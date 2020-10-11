package com.justclean.networking

sealed class RequestType {
    data class GET(val queries: HashMap<String, Any> = HashMap()) : RequestType()
    data class POST(val body: RequestBody) : RequestType()
    data class PUT(val body: RequestBody) : RequestType()
    data class PATCH(val body: RequestBody?) : RequestType()
    object DELETE : RequestType()
}