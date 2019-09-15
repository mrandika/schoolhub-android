package space.mrandika.schoolhub.logic.Auth

import android.content.Context
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import space.mrandika.schoolhub.activity.MainActivity
import space.mrandika.schoolhub.model.Authorization
import space.mrandika.schoolhub.network.Api

internal class AuthPresenter (
    private val context: Context,
    private val view: AuthView,
    private val api: Api,
    private val gson: Gson
) {
    fun signIn(email: String, password: String) {
        view.showLoading()
        doAsync {
            Fuel.post(api.login())
                .body("email=$email&password=$password")
                .responseString { request, response, result ->
                    val status = response.statusCode
                    val name = "token"
                    if (status == 200) {
                        val json = result.get()
                        val authorization = gson.fromJson(json, Authorization::class.java)

                        val sharedPreference = context.getSharedPreferences(name, Context.MODE_PRIVATE)
                        val editor = sharedPreference.edit()
                        editor.putString(name, authorization.token)
                        editor.apply()

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