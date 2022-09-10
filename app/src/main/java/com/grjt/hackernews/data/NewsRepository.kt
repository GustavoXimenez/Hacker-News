package com.grjt.hackernews.data

import com.grjt.hackernews.data.model.NewsModel
import com.grjt.hackernews.data.model.ResponseNews
import com.grjt.hackernews.data.network.NewsService

class NewsRepository {

    private val api = NewsService()

    suspend fun getAllNews(): ResponseNews {
        return api.getNews()
    }

}