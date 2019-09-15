package space.mrandika.schoolhub.logic.Today

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import space.mrandika.schoolhub.R
import space.mrandika.schoolhub.activity.ScannerActivity

class TodayAdapter(private val today: List<TodayResponse>):
    RecyclerView.Adapter<TodayAdapter.TodayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayViewHolder =
        TodayViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_today, parent, false
            )
        )

    override fun getItemCount(): Int = today.size

    override fun onBindViewHolder(holder: TodayViewHolder, position: Int) {
        holder.bindItem(today[position])
    }

    class TodayViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val null_presence: CardView = view.find(R.id.null_presence)
        private val available_presence: CardView = view.find(R.id.available_presence)

        fun bindItem(today: TodayResponse) {
            if (today.is_presenced == 0) {
                val btnAbsen: Button = itemView.find(R.id.btn_absen)
                null_presence.visibility = View.VISIBLE
                btnAbsen.setOnClickListener {
                    itemView.context.startActivity<ScannerActivity>()
                }
            } else {
                available_presence.visibility = View.VISIBLE
            }
        }
    }
}