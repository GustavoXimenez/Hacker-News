package com.grjt.hackernews.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grjt.hackernews.data.model.NewsModel
import com.grjt.hackernews.databinding.CardItemNewsBinding

class NewsAdapter(
    private var nList: List<NewsModel>,
    private val context: Context
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(CardItemNewsBinding.inflate(
            LayoutInflater.from(context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        configInitialNews(holder, nList[position])
    }

    override fun getItemCount(): Int = nList.size

    private fun configInitialNews(
        holder: ViewHolder,
        data: NewsModel
    ) {
        holder.view.apply {
            tvTitle.text = data.storyTitle
            tvAuthor.text = data.author
            tvTime.text = data.createAt
        }
    }

    inner class ViewHolder(val view: CardItemNewsBinding) : RecyclerView.ViewHolder(view.root)

}