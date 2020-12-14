package com.justclean.core.network

import com.justclean.networking.NetworkController
import com.justclean.networking.RequestType
import io.reactivex.Flowable

object ApiManagerRepository {

    fun getLanguages(): Flowable<Model<List<Language>>> = NetworkController
        .processRequest(RequestType.GET(), APIEndpoints.LANGUAGES)

}


