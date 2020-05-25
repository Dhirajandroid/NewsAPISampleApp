package com.newsapi.sampleapp.di.modules

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import com.newsapi.sampleapp.source.local.ArticlesDao
import com.newsapi.sampleapp.source.local.Database
import com.newsapi.sampleapp.viewmodel.ArticlesViewModelFactory
import com.newsapi.sampleapp.utils.Constants
import com.newsapi.sampleapp.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(val app: Application) {

  @Provides
  @Singleton
  fun provideApplication(): Application = app

  @Provides
  @Singleton
  fun provideArticlesDatabase(app: Application): Database = Room.databaseBuilder(app,
      Database::class.java, Constants.DATABASE_NAME)
      .fallbackToDestructiveMigration()
      .build()

  @Provides
  @Singleton
  fun provideArticlesDao(
      database: Database): ArticlesDao = database.articlesDao()

  @Provides
  @Singleton
  fun provideArticlesViewModelFactory(
      factory: ArticlesViewModelFactory): ViewModelProvider.Factory = factory

  @Provides
  @Singleton
  fun provideUtils(): Utils = Utils(app)
}