package com.grjt.hackernews.data.network

import com.grjt.hackernews.data.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiClient {
    @GET("api/v1/search_by_date?query=android")
    suspend fun getAllNews(): Response<List<NewsModel>>
}