package space.mrandika.schoolhub.logic.Subject

interface SubjectView {
    fun showLoading()
    fun hideLoading()
    fun getData(data: List<SubjectResponse>)
}