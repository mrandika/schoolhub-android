package space.mrandika.schoolhub.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_today.view.*
import space.mrandika.schoolhub.R
import space.mrandika.schoolhub.logic.Today.TodayAdapter
import space.mrandika.schoolhub.logic.Today.TodayPresenter
import space.mrandika.schoolhub.logic.Today.TodayResponse
import space.mrandika.schoolhub.logic.Today.TodayView
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class TodayFragment : Fragment(), TodayView {

    internal lateinit var view: View
    private lateinit var textDate: TextView
    private lateinit var progressToday: ProgressBar
    private lateinit var rvToday: RecyclerView

    private var todays: MutableList<TodayResponse> = mutableListOf()
    private lateinit var presenter: TodayPresenter
    private lateinit var adapter: TodayAdapter

    override fun showLoading() {
        rvToday.visibility = View.INVISIBLE
        progressToday.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rvToday.visibility = View.VISIBLE
        progressToday.visibility = View.GONE
    }

    override fun getData(data: List<TodayResponse>) {
        todays.clear()
        todays.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val sdf = SimpleDateFormat("EEEE, dd MMMM")
        val d = Date()

        view = inflater.inflate(R.layout.fragment_today, container, false)

        textDate = view.today_date
        progressToday = view.progress_today
        rvToday = view.rv_today

        textDate.text = sdf.format(d)

        adapter = TodayAdapter(todays)
        rvToday.adapter = adapter
        rvToday.layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
        presenter = TodayPresenter(this.activity!!, this, Gson())
        presenter.getTodayData()

        return view
    }

    override fun onResume() {
        super.onResume()
        presenter = TodayPresenter(this.activity!!, this, Gson())
        presenter.getTodayData()
    }


}
