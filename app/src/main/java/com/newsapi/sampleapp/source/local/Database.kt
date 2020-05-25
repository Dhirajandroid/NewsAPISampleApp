package com.newsapi.sampleapp.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.newsapi.sampleapp.models.Article

@Database(entities = arrayOf(Article::class), version = 1)
abstract class Database : RoomDatabase() {
  abstract fun articlesDao(): ArticlesDao
}