package com.chilik1020.mustachepaws.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.chilik1020.mustachepaws.R
import com.chilik1020.mustachepaws.Screens
import com.chilik1020.mustachepaws.ui.base.BackButtonListener
import com.chilik1020.mustachepaws.utils.APPSCOPE
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.ktp.KTP
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    private val navigator: Navigator = SupportAppNavigator(this, R.id.fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        KTP.openScope(APPSCOPE).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateToLoginScreen()
    }

    private fun navigateToLoginScreen() {
        router.replaceScreen(Screens.LoginScreen())
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment != null && fragment is BackButtonListener) {
            fragment.onBackPressed()
            return
        } else {
            super.onBackPressed()
        }
    }
}
