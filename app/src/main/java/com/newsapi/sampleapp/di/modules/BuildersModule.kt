package com.newsapi.sampleapp.di.modules

import com.newsapi.sampleapp.ui.activity.ArticlesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

  @ContributesAndroidInjector
  abstract fun contributeArticlesActivity(): ArticlesActivity
}