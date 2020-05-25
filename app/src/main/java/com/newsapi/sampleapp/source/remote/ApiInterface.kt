package com.newsapi.sampleapp.source.remote

import com.newsapi.sampleapp.models.ArticleResponse
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiInterface {

  @GET("top-headlines?country=us&apiKey=854a70d5dbe14633b55ce8923cd2202a&pagesize=100")
  fun getArticlesList(): Observable<ArticleResponse>
}