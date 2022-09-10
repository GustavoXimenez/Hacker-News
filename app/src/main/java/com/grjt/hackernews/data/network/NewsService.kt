package com.grjt.hackernews.data.network

import com.grjt.hackernews.data.model.ResponseNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsService @Inject constructor(
    private val api:NewsApiClient
) {
    suspend fun getNews():ResponseNews {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllNews()
                response.body() ?: ResponseNews()
            } catch (e: Exception) {
                ResponseNews()
            }
        }
    }
}