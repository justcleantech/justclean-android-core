package com.justclean.networking

import com.google.gson.JsonElement
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*
import java.time.temporal.TemporalQueries

interface APIService {

    @GET()
    fun getRequest(@Url fullUrl: String, @QueryMap queries: Map<String, String>? = HashMap()): Flowable<JsonElement>

    @POST()
    fun postRequest(@Url fullUrl: String, @Body requestBody: RequestBody): Flowable<JsonElement>

    @PUT()
    fun putRequest(@Url fullUrl: String, @Body requestBody: RequestBody): Flowable<JsonElement>

    @PATCH()
    fun patchRequest(@Url fullUrl: String, @Body requestBody: RequestBody): Flowable<JsonElement>

    @DELETE()
    fun deleteRequest(@Url fullUrl: String): Flowable<JsonElement>

}