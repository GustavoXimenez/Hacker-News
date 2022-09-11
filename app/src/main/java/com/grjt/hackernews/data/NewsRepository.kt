package com.grjt.hackernews.data

import com.grjt.hackernews.data.database.dao.NewsDao
import com.grjt.hackernews.data.database.entities.NewsEntity
import com.grjt.hackernews.data.network.NewsService
import com.grjt.hackernews.domain.model.News
import com.grjt.hackernews.domain.model.toDomain
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsService,
    private val newsDao: NewsDao
) {
    suspend fun getAllNewsFromApi(): ArrayList<News> {
        val response = api.getNews()
        return ArrayList(response.hits.map { it.toDomain() })
    }

    suspend fun getAllNewsFromDatabase(): ArrayList<News> {
        val response = newsDao.getAllNews()
        return ArrayList(response.map { it.toDomain() })
    }

    suspend fun insertNews(news: ArrayList<NewsEntity>){
        newsDao.insertAll(news)
    }

    suspend fun clearNews() {
        newsDao.deleteAllNews()
    }
}