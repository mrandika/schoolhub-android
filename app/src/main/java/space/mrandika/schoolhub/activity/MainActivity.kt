package space.mrandika.schoolhub.activity

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.anko.startActivity
import space.mrandika.schoolhub.R
import space.mrandika.schoolhub.activity.Auth.LoginActivity
import space.mrandika.schoolhub.fragment.TodayFragment
import space.mrandika.schoolhub.logic.Token.TokenPresenter
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

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

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        var fragment: Fragment? = null

        when (menuItem.itemId) {
            R.id.item_today -> fragment = TodayFragment()
        }

        return loadFragment(fragment)
    }
}
