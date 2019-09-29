package space.mrandika.schoolhub.logic.Token

import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.okButton
import org.jetbrains.anko.uiThread
import space.mrandika.schoolhub.activity.Auth.LoginActivity
import space.mrandika.schoolhub.network.Api

class TokenPresenter(
    private val context: Context
) {
    fun getTokenStatus() {
        doAsync {
            val sharedPreference = context.getSharedPreferences("token", Context.MODE_PRIVATE)
            val token = sharedPreference.getString("token", null)

            Fuel.get(Api.checkToken())
                .header("Authorization" to "Bearer $token")
                .responseString { request, response, result ->

                    Log.i("RQST", request.toString())
                    Log.i("RSPN", response.toString())
                    Log.i("RSLT", result.toString())

                    val status = response.statusCode
                    if (status == 419) {

                        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
                        prefs.edit()
                            .remove("token")
                            .apply()

                        uiThread {
                            context.alert("Your session has expired.", "Alert") {
                                okButton {
                                    val i = Intent(context, LoginActivity::class.java)
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    context.startActivity(i)
                                }
                                isCancelable = false
                            }.show()
                        }
                    }
                    if (status == 404 || status == 400) {

                        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
                        prefs.edit()
                            .remove("token")
                            .apply()

                        uiThread {
                            context.alert("Your Session is Invalid.", "Alert") {
                                okButton {
                                    val i = Intent(context, LoginActivity::class.java)
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    context.startActivity(i)
                                }
                                isCancelable = false
                            }.show()
                        }
                    }
                }
        }
    }
}