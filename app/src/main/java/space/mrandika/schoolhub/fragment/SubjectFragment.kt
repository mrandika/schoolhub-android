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
import kotlinx.android.synthetic.main.fragment_subject.view.*

import space.mrandika.schoolhub.R
import space.mrandika.schoolhub.logic.News.NewsAdapter
import space.mrandika.schoolhub.logic.News.NewsPresenter
import space.mrandika.schoolhub.logic.News.NewsResponse
import space.mrandika.schoolhub.logic.Subject.SubjectAdapter
import space.mrandika.schoolhub.logic.Subject.SubjectPresenter
import space.mrandika.schoolhub.logic.Subject.SubjectResponse
import space.mrandika.schoolhub.logic.Subject.SubjectView
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class SubjectFragment : Fragment(), SubjectView {

    internal lateinit var view: View
    private lateinit var textDate: TextView

    private lateinit var rvSubject: RecyclerView
    private lateinit var progressSubject: ProgressBar

    private var subject: MutableList<SubjectResponse> = mutableListOf()
    private lateinit var presenter: SubjectPresenter
    private lateinit var adapter: SubjectAdapter

    override fun showLoading() {
        rvSubject.visibility = View.INVISIBLE
        progressSubject.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rvSubject.visibility = View.VISIBLE
        progressSubject.visibility = View.GONE
    }

    override fun getData(data: List<SubjectResponse>) {
        subject.clear()
        subject.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val sdf = SimpleDateFormat("EEEE, dd MMMM")
        val d = Date()

        view = inflater.inflate(R.layout.fragment_subject, container, false)

        textDate = view.subject_date
        rvSubject = view.rv_subject
        progressSubject = view.progress_subject
        textDate.text = sdf.format(d)

        adapter = SubjectAdapter(subject)
        rvSubject.adapter = adapter
        rvSubject.layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
        presenter = SubjectPresenter(this.activity!!, this, Gson())
        presenter.getteaching()

        return view
    }


}
