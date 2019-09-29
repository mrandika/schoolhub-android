package space.mrandika.schoolhub.logic.News

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.find
import space.mrandika.schoolhub.R

class NewsAdapter(private val news: List<NewsResponse>):
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news, parent, false
            )
        )

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindItem(news[position])
    }

    class NewsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val news_title: TextView = view.find(R.id.news_title)
        private val news_headline: TextView = view.find(R.id.news_headline)
        private val news_content: TextView = view.find(R.id.news_content)
        private val news_author: TextView = view.find(R.id.news_author)

        fun bindItem(news: NewsResponse) {
            news_title.text = news.title
            news_headline.text = news.headline
            news_content.text = Html.fromHtml(news.content)
            news_author.text = "${news.user}, ${news.created_at}"
        }
    }
}