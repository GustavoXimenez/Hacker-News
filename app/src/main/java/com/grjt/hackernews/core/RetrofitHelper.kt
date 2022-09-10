package com.grjt.hackernews.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hn.algolia.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}