package com.justclean.networking

import com.google.gson.JsonElement
import io.reactivex.Flowable
import retrofit2.http.*

interface APIService {

    //RxJava methods return Flowable
    @GET()
    fun getRequest(
        @Url fullUrl: String,
        @QueryMap queries: HashMap<String, Any>
    ): Flowable<JsonElement>

    @POST()
    fun postRequest(@Url fullUrl: String, @Body requestBody: RequestBody): Flowable<JsonElement>

    @PUT()
    fun putRequest(@Url fullUrl: String, @Body requestBody: RequestBody): Flowable<JsonElement>

    @PATCH()
    fun patchRequest(@Url fullUrl: String, @Body requestBody: RequestBody): Flowable<JsonElement>

    @DELETE()
    fun deleteRequest(@Url fullUrl: String): Flowable<JsonElement>

    //Coroutines methods type suspended
    @GET()
    suspend fun getSuspendRequest(
        @Url fullUrl: String,
        @QueryMap queries: HashMap<String, Any>,
        @HeaderMap headers: HashMap<String, Any>
    ): JsonElement

    @POST()
    suspend fun postSuspendRequest(
        @Url fullUrl: String, @Body requestBody: RequestBody,
        @HeaderMap headers: HashMap<String, Any>
    ): JsonElement

    @PUT()
    suspend fun putSuspendRequest(
        @Url fullUrl: String, @Body requestBody: RequestBody,
        @HeaderMap headers: HashMap<String, Any>
    ): JsonElement

    @PATCH()
    suspend fun patchSuspendRequest(
        @Url fullUrl: String, @Body requestBody: RequestBody,
        @HeaderMap headers: HashMap<String, Any>
    ): JsonElement

    @DELETE()
    suspend fun deleteSuspendRequest(@Url fullUrl: String): JsonElement
}