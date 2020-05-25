package com.newsapi.sampleapp.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(
        tableName = "articles"
)

data class Article(

        @Json(name = "author")
        val author: String?,

        @Json(name = "content")
        val content: String?,

        @Json(name = "description")
        val description: String?,

        @PrimaryKey
        @Json(name = "publishedAt")
        val publishedAt: String,

        @Json(name = "title")
        val title: String?,

        @Json(name = "url")
        val url: String?,

        @Json(name = "urlToImage")
        val urlToImage: String?
)  : Serializable

