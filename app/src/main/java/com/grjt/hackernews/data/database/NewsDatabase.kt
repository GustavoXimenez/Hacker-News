package com.grjt.hackernews.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.grjt.hackernews.data.database.dao.NewsDao
import com.grjt.hackernews.data.database.entities.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun getNewsDao(): NewsDao
}