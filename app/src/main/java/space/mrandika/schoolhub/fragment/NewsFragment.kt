package space.mrandika.schoolhub.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_news.view.*
import kotlinx.android.synthetic.main.fragment_subject.view.*

import space.mrandika.schoolhub.R
import space.mrandika.schoolhub.logic.News.NewsAdapter
import space.mrandika.schoolhub.logic.News.NewsPresenter
import space.mrandika.schoolhub.logic.News.NewsResponse
import space.mrandika.schoolhub.logic.News.NewsView
import space.mrandika.schoolhub.logic.Today.TodayAdapter
import space.mrandika.schoolhub.logic.Today.TodayPresenter
import space.mrandika.schoolhub.logic.Today.TodayResponse
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : Fragment(), NewsView {

    internal lateinit var view: View
    private lateinit var textDate: TextView
    private lateinit var rvNews: RecyclerView
    private lateinit var progressNews: ProgressBar

    private var news: MutableList<NewsResponse> = mutableListOf()
    private lateinit var presenter: NewsPresenter
    private lateinit var adapter: NewsAdapter

    override fun showLoading() {
        rvNews.visibility = View.INVISIBLE
        progressNews.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rvNews.visibility = View.VISIBLE
        progressNews.visibility = View.GONE
    }

    override fun getData(data: List<NewsResponse>) {
        news.clear()
        news.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val sdf = SimpleDateFormat("EEEE, dd MMMM")
        val d = Date()

        view = inflater.inflate(R.layout.fragment_news, container, false)

        textDate = view.news_date
        rvNews = view.rv_news
        progressNews = view.progress_news
        textDate.text = sdf.format(d)

        adapter = NewsAdapter(news)
        rvNews.adapter = adapter
        rvNews.layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
        presenter = NewsPresenter(this.activity!!, this, Gson())
        presenter.getallpost()

        return view
    }


}
