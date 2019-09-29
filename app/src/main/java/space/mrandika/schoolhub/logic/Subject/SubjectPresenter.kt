package space.mrandika.schoolhub.logic.Subject

import android.content.Context
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import space.mrandika.schoolhub.logic.Today.Today
import space.mrandika.schoolhub.logic.Today.TodayView
import space.mrandika.schoolhub.network.Api

class SubjectPresenter (
    private val context: Context,
    private val view: SubjectView,
    private val gson: Gson
) {
    fun getteaching() {
        view.showLoading()
        doAsync {
            val sharedPreference = context.getSharedPreferences("token", Context.MODE_PRIVATE)
            val token = sharedPreference.getString("token", null)

            Fuel.get(Api.teaching())
                .header("Authorization" to "Bearer $token")
                .responseString { request, response, result ->

                    Log.i("RQST", request.toString())
                    Log.i("RSPN", response.toString())
                    Log.i("RSLT", result.toString())

                    val status = response.statusCode
                    if (status == 200 && !result.toString().contains("Token")) {
                        val json = result.get()
                        val responseJson = gson.fromJson(json, Subject::class.java)

                        uiThread {
                            view.hideLoading()
                            view.getData(responseJson.data)
                        }
                    }
                }
        }
    }
}