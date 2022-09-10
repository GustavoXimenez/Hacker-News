package com.grjt.hackernews.data.model

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("created_at")
    val createAt: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("author")
    val author: String = "",
    @SerializedName("story_id")
    val storyId: String = "",
    @SerializedName("story_title")
    val storyTitle: String = "",
    @SerializedName("story_url")
    val storyUrl: String = ""
)