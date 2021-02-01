package com.justclean.sample.network

data class Model<Data>(
    val data: Data,
    var message: String,
    var status: Boolean,
    var code: Int
)