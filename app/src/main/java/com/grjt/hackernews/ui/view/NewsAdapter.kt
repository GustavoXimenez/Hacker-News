package com.grjt.hackernews.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.grjt.hackernews.databinding.CardItemNewsBinding
import com.grjt.hackernews.domain.model.News

class NewsAdapter(
    private var nList: ArrayList<News>,
    private val context: Context,
    private var onClickNews: ((News) -> Unit)? = null
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
        data: News
    ) {
        holder.view.apply {
            tvTitle.text = if(data.storyTitle.isNullOrEmpty()) "No title" else data.storyTitle
            tvAuthor.text = if(data.author.isNullOrEmpty()) "No author" else data.author
            tvTime.text = if(data.createAt.isNullOrEmpty()) "No datetime" else data.createAt
            setOnClickListenerNews(data, cardNews)
        }
    }

    private fun setOnClickListenerNews(news: News, cardNews: CardView) {
        if(onClickNews != null) {
            cardNews.setOnClickListener {
                onClickNews?.invoke(news)
            }
        }
    }

    inner class ViewHolder(val view: CardItemNewsBinding) : RecyclerView.ViewHolder(view.root)

}