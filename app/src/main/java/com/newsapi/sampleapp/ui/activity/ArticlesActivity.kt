package com.newsapi.sampleapp.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.newsapi.sampleapp.R
import com.newsapi.sampleapp.R.layout
import com.newsapi.sampleapp.models.Article
import com.newsapi.sampleapp.ui.adapter.ArticlesAdapter
import com.newsapi.sampleapp.utils.Constants
import com.newsapi.sampleapp.utils.InfiniteScrollListener
import com.newsapi.sampleapp.viewmodel.ArticlesViewModel
import com.newsapi.sampleapp.viewmodel.ArticlesViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_articles.progressBar
import kotlinx.android.synthetic.main.activity_articles.recycler
import java.util.ArrayList
import javax.inject.Inject

class ArticlesActivity : AppCompatActivity() {

  @Inject
  lateinit var articlesViewModelFactory: ArticlesViewModelFactory
  var articlesAdapter = ArticlesAdapter(ArrayList())
  lateinit var articlesViewModel: ArticlesViewModel
  var currentPage = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_articles)
    AndroidInjection.inject(this)

    initializeRecycler()

    articlesViewModel = ViewModelProviders.of(this, articlesViewModelFactory).get(
        ArticlesViewModel::class.java)

    progressBar.visibility = View.VISIBLE
    loadData()

    articlesViewModel.result().observe(this,
        Observer<List<Article>> {
          if (it != null) {
            val position = articlesAdapter.itemCount
            articlesAdapter.addArticles(it)
            recycler.adapter = articlesAdapter
            recycler.scrollToPosition(position - Constants.LIST_SCROLLING)
          }
        })

    articlesViewModel.articlesError().observe(this, Observer<String> {
      if (it != null) {
        Toast.makeText(this, resources.getString(R.string.error_message) + it,
            Toast.LENGTH_SHORT).show()
      }
    })

    articlesViewModel.articlesLoader().observe(this, Observer<Boolean> {
      if (it == false) progressBar.visibility = View.GONE
    })
  }

  private fun initializeRecycler() {
    val gridLayoutManager = GridLayoutManager(this, 1)
    gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
    recycler.apply {
      setHasFixedSize(true)
      layoutManager = gridLayoutManager
      addOnScrollListener(InfiniteScrollListener({ loadData() }, gridLayoutManager))
    }
  }

  fun loadData() {
    articlesViewModel.loadArticles(Constants.LIMIT, currentPage * Constants.OFFSET)
    currentPage++
  }

  override fun onDestroy() {
    articlesViewModel.disposeElements()
    super.onDestroy()
  }
}
