package com.newsapi.sampleapp.repository

import android.util.Log
import com.newsapi.sampleapp.models.Article
import com.newsapi.sampleapp.models.ArticleResponse
import com.newsapi.sampleapp.source.local.ArticlesDao
import com.newsapi.sampleapp.source.remote.ApiInterface
import com.newsapi.sampleapp.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject


class ArticlesRepository @Inject constructor(val apiInterface: ApiInterface,
                                             val articlesDao: ArticlesDao, val utils: Utils) {

  fun getArticles(limit: Int, offset: Int): Observable<List<Article>> {
    val hasConnection = utils.isConnectedToInternet()
    var observableFromApi: Observable<List<Article>>? = null
    if (hasConnection){
      observableFromApi = getArticlesListFromApi().map { t: ArticleResponse -> t.articles }
    }
    val observableFromDb = getArticlesListFromDb(limit, offset)

    return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
    else observableFromDb
  }

  fun getArticlesListFromApi(): Observable<ArticleResponse> {
      return apiInterface.getArticlesList()
        .doOnNext {
          Log.e("REPOSITORY API * ", it.toString())
          for (item in it.articles!!) {
            articlesDao.insertArticles(item)
          }
        }
              .doOnError {
                  Log.e("doOnError", "Exception Occured"+it);
              }

  }

  fun getArticlesListFromDb(limit: Int, offset: Int): Observable<List<Article>> {
    return articlesDao.queryArticles(limit, offset)
        .toObservable()
        .doOnNext {
          Log.e("REPOSITORY DB *** ", it.toString())
        }
  }
}