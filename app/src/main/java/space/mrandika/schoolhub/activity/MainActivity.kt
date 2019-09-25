package space.mrandika.schoolhub.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import space.mrandika.schoolhub.R
import space.mrandika.schoolhub.activity.Auth.LoginActivity
import space.mrandika.schoolhub.fragment.SubjectFragment
import space.mrandika.schoolhub.fragment.TodayFragment
import space.mrandika.schoolhub.logic.Token.TokenPresenter
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: TokenPresenter

    override fun onBackPressed() {
        super.onBackPressed()

        this.finishAffinity()
        exitProcess(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference = this.getSharedPreferences("token", Context.MODE_PRIVATE)
        val token = sharedPreference.getString("token", null)

        if (token == null) {
            startActivity<LoginActivity>()
        }

        presenter = TokenPresenter(this)
        presenter.getTokenStatus()

        loadFragment(TodayFragment())

        bottomNavigationView.setOnNavigationItemSelectedListener {menuItem ->
            var fragment: Fragment?
            var status: Boolean? = null

            Log.i("MENU", menuItem.itemId.toString())

            when (menuItem.itemId) {
                R.id.item_today -> {
                    fragment = TodayFragment()
                    status = loadFragment(fragment)
                }
                R.id.item_subject -> {
                    fragment = SubjectFragment()
                    status = loadFragment(fragment)
                }
                R.id.item_camera -> {
                    startActivity<ScannerActivity>()
                    status = true
                }
            }

            status!!
        }
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_root, fragment)
                .commit()
            return true
        }
        return false
    }


}
