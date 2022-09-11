package com.grjt.hackernews.data.model

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("created_at")
    val createAt: String? = "",
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("story_id")
    val storyId: Int? = 0,
    @SerializedName("story_title")
    val storyTitle: String? = "",
    @SerializedName("story_url")
    val storyUrl: String? = ""
)