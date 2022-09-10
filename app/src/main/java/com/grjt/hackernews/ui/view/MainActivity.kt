package com.grjt.hackernews.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.grjt.hackernews.databinding.ActivityMainBinding
import com.grjt.hackernews.ui.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        newsViewModel.newsModel.observe(this) { news ->
            Log.d("ViewModel", "Title: ${news[0].storyTitle} - Author: ${news[0].author}")
        }

        newsViewModel.getNews()
    }
}