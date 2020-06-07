package com.justclean.core.data.remote

import com.google.gson.annotations.SerializedName

data class PageInfo(
    @SerializedName("page_count") val pageCount: Int,
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("next_page") val nextPage: Int?,
    @SerializedName("item_count") val itemCount: Int
)