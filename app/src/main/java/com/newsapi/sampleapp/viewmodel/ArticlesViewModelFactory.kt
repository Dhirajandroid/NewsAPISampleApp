package com.newsapi.sampleapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject


class ArticlesViewModelFactory @Inject constructor(
    private val articlesViewModel: ArticlesViewModel) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(ArticlesViewModel::class.java!!)) {
      return articlesViewModel as T
    }
    throw IllegalArgumentException("Unknown class name")
  }
}