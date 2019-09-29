package space.mrandika.schoolhub.logic.News

import space.mrandika.schoolhub.logic.Today.TodayResponse

interface NewsView {
    fun showLoading()
    fun hideLoading()
    fun getData(data: List<NewsResponse>)
}