package space.mrandika.schoolhub.logic.Today

import android.content.Context
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import space.mrandika.schoolhub.network.Api

class TodayPresenter(
    private val context: Context,
    private val view: TodayView,
    private val gson: Gson
) {
    fun getTodayData() {
        view.showLoading()
        doAsync {
            val sharedPreference = context.getSharedPreferences("token", Context.MODE_PRIVATE)
            val token = sharedPreference.getString("token", null)

            Fuel.get(Api.today())
                .header("Authorization" to "Bearer $token")
                .responseString { request, response, result ->

                    Log.i("RQST", request.toString())
                    Log.i("RSPN", response.toString())
                    Log.i("RSLT", result.toString())

                    val status = response.statusCode
                    if (status == 200) {
                        val json = result.get()
                        val responseJson = gson.fromJson(json, Today::class.java)

                        uiThread {
                            view.hideLoading()
                            view.getData(responseJson.data)
                        }
                    }
                }
        }
    }
}