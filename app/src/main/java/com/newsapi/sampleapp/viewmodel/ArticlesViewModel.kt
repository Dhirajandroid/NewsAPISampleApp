package com.newsapi.sampleapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.newsapi.sampleapp.models.Article
import com.newsapi.sampleapp.repository.ArticlesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject


class ArticlesViewModel @Inject constructor(
    private val articlesRepository: ArticlesRepository) : ViewModel() {

  var result: MutableLiveData<List<Article>> = MutableLiveData()
  var error: MutableLiveData<String> = MutableLiveData()
  var loader: MutableLiveData<Boolean> = MutableLiveData()
  lateinit var disposableObserver: DisposableObserver<List<Article>>

  fun result(): LiveData<List<Article>> {
    return result
  }

  fun articlesError(): LiveData<String> {
    return error
  }

  fun articlesLoader(): LiveData<Boolean> {
    return loader
  }

  fun loadArticles(limit: Int, offset: Int) {

    disposableObserver = object : DisposableObserver<List<Article>>() {
      override fun onComplete() {

      }

      override fun onNext(list: List<Article>) {
        result.postValue(list)
        loader.postValue(false)
      }

      override fun onError(e: Throwable) {
        error.postValue(e.message)
        loader.postValue(false)
      }
    }

    articlesRepository.getArticles(limit, offset)
            .subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.debounce(400, MILLISECONDS)
            ?.subscribe(disposableObserver)
  }

  fun disposeElements(){
    if(null != disposableObserver && !disposableObserver.isDisposed) disposableObserver.dispose()
  }

}