package space.mrandika.schoolhub.logic.Sarpras

import android.content.Context
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import space.mrandika.schoolhub.activity.MainActivity
import space.mrandika.schoolhub.logic.News.News
import space.mrandika.schoolhub.network.Api

class BorrowPresenter(
    private val context: Context,
    private val view: BorrowView,
    private val gson: Gson
) {
    fun borrow(id: String) {
        view.showLoading()
        doAsync {
            val sharedPreference = context.getSharedPreferences("token", Context.MODE_PRIVATE)
            val token = sharedPreference.getString("token", null)

            Fuel.get(Api.borrow(id))
                .header("Authorization" to "Bearer $token")
                .responseString { request, response, result ->

                    Log.i("RQST", request.toString())
                    Log.i("RSPN", response.toString())
                    Log.i("RSLT", result.toString())

                    val status = response.statusCode
                    if (status == 200 && !result.toString().contains("Token")) {
                        context.startActivity<MainActivity>()

                        uiThread {
                            view.hideLoading()
                        }
                    }
                }
        }
    }
}