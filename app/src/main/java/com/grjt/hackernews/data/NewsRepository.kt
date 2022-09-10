package com.grjt.hackernews.data

import com.grjt.hackernews.data.model.ResponseNews
import com.grjt.hackernews.data.network.NewsService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsService
) {
    suspend fun getAllNews(): ResponseNews = api.getNews()
}