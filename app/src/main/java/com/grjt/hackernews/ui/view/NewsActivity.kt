package com.grjt.hackernews.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import com.grjt.hackernews.core.ConstantsUtils.CONST_URL_NEW
import com.grjt.hackernews.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getStringExtra(CONST_URL_NEW)?.let { url ->
            setWebView(url)
        }

        onBackPressedDispatcher.addCallback {
            finish()
        }
    }

    private fun setWebView(url: String) {
        binding.wvNews.settings.javaScriptEnabled = true
        binding.wvNews.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progress.visibility = View.GONE
            }
        }
        binding.wvNews.loadUrl(url)
    }

}