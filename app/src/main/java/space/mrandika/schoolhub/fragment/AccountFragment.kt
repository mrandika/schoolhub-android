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
import kotlinx.android.synthetic.main.fragment_account.view.*
import kotlinx.android.synthetic.main.fragment_news.view.*

import space.mrandika.schoolhub.R
import space.mrandika.schoolhub.logic.Grade.GradeAdapter
import space.mrandika.schoolhub.logic.Grade.GradePresenter
import space.mrandika.schoolhub.logic.Grade.GradeResponse
import space.mrandika.schoolhub.logic.Grade.GradeView
import space.mrandika.schoolhub.logic.News.NewsAdapter
import space.mrandika.schoolhub.logic.News.NewsPresenter
import space.mrandika.schoolhub.logic.News.NewsResponse

/**
 * A simple [Fragment] subclass.
 */
class AccountFragment : Fragment(), GradeView {

    internal lateinit var view: View
    private lateinit var rvGrade: RecyclerView
    private lateinit var progressGrade: ProgressBar

    private var grade: MutableList<GradeResponse> = mutableListOf()
    private lateinit var presenter: GradePresenter
    private lateinit var adapter: GradeAdapter

    override fun showLoading() {
        rvGrade.visibility = View.INVISIBLE
        progressGrade.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rvGrade.visibility = View.VISIBLE
        progressGrade.visibility = View.GONE
    }

    override fun getData(data: List<GradeResponse>) {
        grade.clear()
        grade.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false)

        rvGrade = view.rv_grade
        progressGrade = view.progress_grade

        adapter = GradeAdapter(grade)
        rvGrade.adapter = adapter
        rvGrade.layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
        presenter = GradePresenter(this.activity!!, this, Gson())
        presenter.getgrade()

        return view
    }


}
