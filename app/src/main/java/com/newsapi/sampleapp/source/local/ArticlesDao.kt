package com.newsapi.sampleapp.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.newsapi.sampleapp.models.Article
import io.reactivex.Single

@Dao
interface ArticlesDao {

  @Query("SELECT * FROM articles limit :limit offset :offset")
  fun queryArticles(limit:Int, offset:Int): Single<List<Article>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertArticles(article: Article)

}