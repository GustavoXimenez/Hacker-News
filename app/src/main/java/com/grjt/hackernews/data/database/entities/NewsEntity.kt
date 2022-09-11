package com.grjt.hackernews.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.grjt.hackernews.domain.model.News

@Entity(tableName = "news_table")
data class NewsEntity (
    @ColumnInfo(name = "createAt")
    val createAt: String? = "",
    @ColumnInfo(name = "author")
    val author: String? = "",
    @PrimaryKey
    @ColumnInfo(name = "storyId")
    val storyId: Int? = 0,
    @ColumnInfo(name = "storyTitle")
    val storyTitle: String? = "",
    @ColumnInfo(name = "storyUrl")
    val storyUrl: String? = "",
    @ColumnInfo(name = "deleted")
    val deleted: Boolean = false
)

fun News.toDatabase() = NewsEntity(
    createAt,author, storyId, storyTitle, storyUrl, deleted
)