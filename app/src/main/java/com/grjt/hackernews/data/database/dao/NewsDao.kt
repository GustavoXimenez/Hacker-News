package com.grjt.hackernews.data.database.dao

import androidx.room.*
import com.grjt.hackernews.data.database.entities.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    suspend fun getAllNews(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<NewsEntity>)

    @Update
    suspend fun updateNew(news : NewsEntity)

    @Query("DELETE FROM news_table")
    suspend fun deleteAllNews()
}