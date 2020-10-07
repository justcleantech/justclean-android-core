package com.justclean.networking

import com.google.gson.JsonElement
import io.reactivex.Flowable
import retrofit2.http.*

interface APIService {

    @GET()
    fun getRequest(@Url fullUrl: String, @QueryMap queries: HashMap<String, Any>): Flowable<JsonElement>

    @POST()
    fun postRequest(@Url fullUrl: String, @Body requestBody: RequestBody): Flowable<JsonElement>

    @PUT()
    fun putRequest(@Url fullUrl: String, @Body requestBody: RequestBody): Flowable<JsonElement>

    @PATCH()
    fun patchRequest(@Url fullUrl: String, @Body requestBody: RequestBody): Flowable<JsonElement>

    @DELETE()
    fun deleteRequest(@Url fullUrl: String): Flowable<JsonElement>

}