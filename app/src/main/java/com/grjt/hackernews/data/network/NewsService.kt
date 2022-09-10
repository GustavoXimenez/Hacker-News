package com.grjt.hackernews.data.network

import android.util.Log
import com.grjt.hackernews.core.RetrofitHelper
import com.grjt.hackernews.data.local.NewsProvider
import com.grjt.hackernews.data.model.NewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getNews():List<NewsModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = retrofit.create(NewsApiClient::class.java).getAllNews()
                response.body() ?: emptyList()
            } catch (e: Exception) {
                NewsProvider.news
            }
        }
    }

}