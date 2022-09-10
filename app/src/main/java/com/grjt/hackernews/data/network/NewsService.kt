package com.grjt.hackernews.data.network

import android.util.Log
import com.grjt.hackernews.core.RetrofitHelper
import com.grjt.hackernews.data.local.NewsProvider
import com.grjt.hackernews.data.model.NewsModel
import com.grjt.hackernews.data.model.ResponseNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getNews():ResponseNews {
        return withContext(Dispatchers.IO) {
            try {
                val response = retrofit.create(NewsApiClient::class.java).getAllNews()
                response.body() ?: ResponseNews()
            } catch (e: Exception) {
                ResponseNews()
            }
        }
    }

}