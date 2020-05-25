package com.newsapi.sampleapp.models

import com.squareup.moshi.Json
import java.io.Serializable

data class ArticleResponse(

        @Json(name = "articles")
        val articles: List<Article>?,

        @Json(name = "status")
        val status: String?,

        @Json(name = "totalResults")
        val totalResults: Int?
): Serializable