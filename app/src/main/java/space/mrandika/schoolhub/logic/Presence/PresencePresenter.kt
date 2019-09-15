package space.mrandika.schoolhub.logic.Presence

import android.content.Context
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import space.mrandika.schoolhub.activity.MainActivity
import space.mrandika.schoolhub.network.Api

class PresencePresenter(
    private val context: Context,
    private val view: PresenceView,
    private val api: Api
) {
    fun postPresence(token: String, attendance: String) {
        view.showLoading()
        doAsync {
            Fuel.post(api.presence())
                .header("Authorization" to "Bearer $token")
                .body("id_attendance=$attendance&status=Hadir")
                .response { request, response, result ->
                    Log.i("RQST", request.toString())
                    Log.i("RSPN", response.toString())
                    Log.i("RSLT", result.toString())

                    val status = response.statusCode

                    if (status == 200) {
                        context.startActivity<MainActivity>()
                    } else {
                        uiThread {
                            view.hideLoading()
                        }
                    }
                }
        }
    }
}