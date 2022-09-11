package com.grjt.hackernews.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.grjt.hackernews.core.ConstantsUtils.CONST_URL_NEW
import com.grjt.hackernews.core.Resource
import com.grjt.hackernews.databinding.ActivityMainBinding
import com.grjt.hackernews.domain.model.News
import com.grjt.hackernews.ui.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var adapter: NewsAdapter
    private var onInternalClickNews: ((News) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsViewModel.getNews().observe(this) { result ->
            when (result) {
                is Resource.Loading -> binding.progress.visibility = View.VISIBLE
                is Resource.Success -> {
                    adapter = NewsAdapter(result.data, this, onInternalClickNews)
                    binding.rvNews.adapter = adapter
                    binding.progress.visibility = View.GONE
                }
                is Resource.Failure -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, result.exception.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        onInternalClickNews = { news ->
            val intent = Intent(this, NewsActivity::class.java)
            intent.putExtra(CONST_URL_NEW, news.storyUrl)
            startActivity(intent)
        }
    }
}