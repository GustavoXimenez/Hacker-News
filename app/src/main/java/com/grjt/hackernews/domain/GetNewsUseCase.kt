package com.grjt.hackernews.domain

import com.grjt.hackernews.core.ConstantsUtils.QUERY_SEARCH
import com.grjt.hackernews.data.NewsRepository
import com.grjt.hackernews.data.model.NewsModel

class GetNewsUseCase {

    private val repository = NewsRepository()

    suspend operator fun invoke(): List<NewsModel> =
        repository.getAllNews()


}