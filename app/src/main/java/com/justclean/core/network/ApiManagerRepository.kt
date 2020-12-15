package com.justclean.core.network

import com.justclean.networking.NetworkController
import com.justclean.networking.RequestType
import io.reactivex.Flowable

object ApiManagerRepository {

    //RxJava method
    fun getLanguages(): Flowable<Model<List<Language>>> =
        NetworkController.processRequest(RequestType.GET(), APIEndpoints.LANGUAGES)

    //Coroutine method
    suspend fun getSuspendLanguages(): Model<List<Language>> =
        NetworkController.processRequest(APIEndpoints.LANGUAGES, RequestType.GET())
}


