package com.justclean.core.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Model<Data>(@SerializedName("page_info")
                       val pageInfo: PageInfo, @SerializedName("data")
                       @Expose
                       val data: Data, @SerializedName("message")
                       @Expose
                       var message: String, @SerializedName("status")
                       @Expose
                       var status: Boolean, @SerializedName("code")
                       @Expose
                       var code: Int)