package space.mrandika.schoolhub.logic.Grade

interface GradeView {
    fun showLoading()
    fun hideLoading()
    fun getData(data: List<GradeResponse>)
}