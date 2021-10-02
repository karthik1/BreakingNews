package com.example.breakingnews.data

import com.example.breakingnews.api.NewsApi
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val newsArticleDb: NewsArticleDatabase
) {
    private val newsArticleDao = newsArticleDb.newsArticleDao()

    suspend fun getBreakingNews(): List<NewsArticle> {
        val response = newsApi.getBreakingNews()
        val serverBreakingNewsArticles = response.articles
        val breakingNewsArticles = serverBreakingNewsArticles.map { serverBreakingNewsArticle ->
            NewsArticle(
                title = serverBreakingNewsArticle.title,
                url = serverBreakingNewsArticle.url,
                thumbnailUrl = serverBreakingNewsArticle.urlToImage,
                isBookmarked = false
            )
        }
        return breakingNewsArticles
    }
}