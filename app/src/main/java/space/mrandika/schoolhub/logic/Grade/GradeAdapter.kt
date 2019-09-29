package space.mrandika.schoolhub.logic.Grade

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.find
import space.mrandika.schoolhub.R

class GradeAdapter(private val grade: List<GradeResponse>):
    RecyclerView.Adapter<GradeAdapter.GradeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder =
        GradeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_grade, parent, false
            )
        )

    override fun getItemCount(): Int = grade.size

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        holder.bindItem(grade[position])
    }

    class GradeViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val grade_section: TextView = view.find(R.id.grade_section)
        private val grade_section_name: TextView = view.find(R.id.grade_section_name)
        private val grade_score: TextView = view.find(R.id.grade_score)

        fun bindItem(grade: GradeResponse) {
            grade_section.text = grade.section
            grade_section_name.text = grade.section_name
            grade_score.text = grade.score.toString()
        }
    }
}