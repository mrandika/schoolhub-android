package space.mrandika.schoolhub.logic.Today

interface TodayView {
    fun showLoading()
    fun hideLoading()
    fun getData(data: List<TodayResponse>)
}