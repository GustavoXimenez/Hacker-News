package com.grjt.hackernews.data.model

import com.google.gson.annotations.SerializedName

data class ResponseNews(
    @SerializedName("hits")
    val hits: List<NewsModel> = emptyList(),
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("nbPages")
    val nbPages: Int = 0,
    @SerializedName("author")
    val params: String = "",
)