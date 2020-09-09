package com.justclean.core.network

import com.justclean.networking.Model
import com.justclean.networking.NetworkController
import com.justclean.networking.RequestType
import io.reactivex.rxjava3.core.Flowable

object ApiManagerRepository {

    fun getLanguages(): Flowable<Model<List<Language>>> = NetworkController
        .processRequest(RequestType.GET(), APIEndpoints.LANGUAGES)
        .map { JsonDeserializer.getLanguages(it) }

}


