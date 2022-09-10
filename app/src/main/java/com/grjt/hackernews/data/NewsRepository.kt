package com.grjt.hackernews.data

import com.grjt.hackernews.data.model.NewsModel
import com.grjt.hackernews.data.network.NewsService

class NewsRepository {

    private val api = NewsService()

    suspend fun getAllNews(): List<NewsModel> {
        return api.getNews()
    }

}