package space.mrandika.schoolhub.activity.Auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import space.mrandika.schoolhub.R
import space.mrandika.schoolhub.logic.Auth.AuthPresenter
import space.mrandika.schoolhub.logic.Auth.AuthView
import space.mrandika.schoolhub.network.Api

class LoginActivity : AppCompatActivity(), AuthView {

    override fun showLoading() {
        progress_login.visibility = View.VISIBLE
        btn_login.visibility = View.INVISIBLE
        btn_register.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        progress_login.visibility = View.GONE
        btn_login.visibility = View.VISIBLE
        btn_register.visibility = View.VISIBLE
    }

    private lateinit var presenter: AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            val email = input_email.text.toString()
            val password = input_password.text.toString()
            presenter = AuthPresenter(this, this, Api, Gson())
            presenter.signIn(email, password)
        }
    }
}
