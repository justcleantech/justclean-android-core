package com.justclean.networking

sealed class RequestType {
    data class GET(val queries: HashMap<String, Any> = HashMap()) : RequestType()
    data class POST(val body: RequestBody = EmptyBody()) : RequestType()
    data class PUT(val body: RequestBody = EmptyBody()) : RequestType()
    data class PATCH(val body: RequestBody = EmptyBody()) : RequestType()
    object DELETE : RequestType()
}