package com.grjt.hackernews.domain.model

import com.grjt.hackernews.data.database.entities.NewsEntity
import com.grjt.hackernews.data.model.NewsModel

data class News(
    val createAt: String? = "",
    val author: String? = "",
    val storyId: Int? = 0,
    val storyTitle: String? = "",
    val storyUrl: String? = "",
    val deleted: Boolean = false
)

fun NewsModel.toDomain() = News(
    createAt, author, storyId, storyTitle, storyUrl
)

fun NewsEntity.toDomain() = News(
    createAt, author, storyId, storyTitle, storyUrl, deleted
)