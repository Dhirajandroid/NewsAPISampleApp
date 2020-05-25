package com.newsapi.sampleapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.newsapi.sampleapp.R
import com.newsapi.sampleapp.models.Article
import com.newsapi.sampleapp.ui.adapter.ArticlesAdapter.ViewHolder
import com.newsapi.sampleapp.utils.DateUtilityMethods
import java.util.*


class ArticlesAdapter(
        articles: List<Article>?) : RecyclerView.Adapter<ViewHolder>() {

  private var articlesList = ArrayList<Article>()

  init {
    this.articlesList = articles as ArrayList<Article>
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item,
        parent, false)
    return ViewHolder(itemView)
  }

  override fun getItemCount(): Int {
    return articlesList.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val article = articlesList[position]
    holder.articlesListItem(article)
  }

  fun addArticles(articles: List<Article>){
    val initPosition = articlesList.size
    articlesList.addAll(articles)
    notifyItemRangeInserted(initPosition, articlesList.size)
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var title = itemView.findViewById<TextView>(R.id.title_card)
    var description = itemView.findViewById<TextView>(R.id.trail_text_card)
    var image = itemView.findViewById<ImageView>(R.id.thumbnail_image_card)
    var author = itemView.findViewById<TextView>(R.id.author_card)
    var date = itemView.findViewById<TextView>(R.id.date_card)

    fun articlesListItem(item: Article) {
      title.text = item.title
      description.text = item.description
      author.text = item.author
      date.text = DateUtilityMethods.getTimeDifference(DateUtilityMethods.formatDate(item.publishedAt));

      Glide.with(itemView.context).load(item.urlToImage)
              .placeholder(R.drawable.loading)
              .centerCrop()
              .into(image)

    }
  }

  }
