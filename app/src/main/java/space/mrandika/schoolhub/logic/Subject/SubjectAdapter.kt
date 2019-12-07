package space.mrandika.schoolhub.logic.Subject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.find
import space.mrandika.schoolhub.R

class SubjectAdapter(private val subject: List<SubjectResponse>):
    RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder =
        SubjectViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_subject, parent, false
            )
        )

    override fun getItemCount(): Int = subject.size

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bindItem(subject[position])
    }

    class SubjectViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val subject_teacher: TextView = view.find(R.id.subject_teacher)
        private val subject_title: TextView = view.find(R.id.subject_title)
        private val subject_time: TextView = view.find(R.id.subject_time)

        fun bindItem(subject: SubjectResponse) {
            subject_teacher.text = subject.teacher
            subject_title.text = "${subject.subject}"
            subject_time.text = "${subject.room_name}, ${subject.time_in} - ${subject.time_out}"
        }
    }
}