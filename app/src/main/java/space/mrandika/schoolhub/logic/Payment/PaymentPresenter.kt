package space.mrandika.schoolhub.logic.Payment

import android.content.Context
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import space.mrandika.schoolhub.activity.MainActivity
import space.mrandika.schoolhub.logic.Presence.PresenceView
import space.mrandika.schoolhub.network.Api

class PaymentPresenter(
    private val context: Context,
    private val api: Api
) {
    fun payment(id: String) {
        doAsync {
            val sharedPreference = context.getSharedPreferences("token", Context.MODE_PRIVATE)
            val token = sharedPreference.getString("token", null)
            Fuel.get(api.payment(id))
                .header("Authorization" to "Bearer $token")
                .response { request, response, result ->
                    Log.i("RQST", request.toString())
                    Log.i("RSPN", response.toString())
                    Log.i("RSLT", result.toString())

                    val status = response.statusCode

                    if (status == 200) {
                        context.startActivity<MainActivity>()
                    }
                }
        }
    }
}