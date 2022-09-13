package com.grjt.hackernews.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.grjt.hackernews.R
import com.grjt.hackernews.core.ConstantsUtils.CONST_URL_NEW
import com.grjt.hackernews.core.Resource
import com.grjt.hackernews.databinding.ActivityMainBinding
import com.grjt.hackernews.domain.model.News
import com.grjt.hackernews.ui.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var adapter: NewsAdapter
    private var onInternalClickNews: ((News) -> Unit)? = null
    private lateinit var newsList: ArrayList<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getNewsList()

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding.rvNews)

        onInternalClickNews = { news ->
            val intent = Intent(this, NewsActivity::class.java)
            intent.putExtra(CONST_URL_NEW, news.storyUrl)
            startActivity(intent)
        }

        binding.srNews.setOnRefreshListener {
            newsViewModel.getNews().observe(this@MainActivity) { result ->
                when (result) {
                    is Resource.Loading -> binding.progress.visibility = View.VISIBLE
                    is Resource.Success -> {
                        newsList = result.data
                        adapter.notifyDataSetChanged()
                        binding.progress.visibility = View.GONE
                        binding.srNews.isRefreshing = false
                    }
                    is Resource.Failure -> {
                        binding.progress.visibility = View.GONE
                        Toast.makeText(this, result.exception.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    val swipeGesture = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.absoluteAdapterPosition
            val itemNews = newsList[position]
            itemNews.apply { deleted = true }
            newsViewModel.deleteNews(itemNews).observe(this@MainActivity) { result ->
                when (result) {
                    is Resource.Loading -> binding.progress.visibility = View.VISIBLE
                    is Resource.Success -> {
                        adapter.deleteItem(position)
                        binding.progress.visibility = View.GONE
                    }
                    is Resource.Failure -> {
                        Toast.makeText(this@MainActivity, getString(R.string.error_delete_news), Toast.LENGTH_LONG).show()
                        binding.progress.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun getNewsList() {
        newsViewModel.getNews().observe(this@MainActivity) { result ->
            when (result) {
                is Resource.Loading -> binding.progress.visibility = View.VISIBLE
                is Resource.Success -> {
                    newsList = result.data
                    adapter = NewsAdapter(newsList, this, onInternalClickNews)
                    binding.rvNews.adapter = adapter
                    binding.progress.visibility = View.GONE
                }
                is Resource.Failure -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, result.exception.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}