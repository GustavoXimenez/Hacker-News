package com.grjt.hackernews.domain

import com.grjt.hackernews.data.NewsRepository
import com.grjt.hackernews.data.model.NewsModel
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): ArrayList<NewsModel> =
        repository.getAllNews().hits
}