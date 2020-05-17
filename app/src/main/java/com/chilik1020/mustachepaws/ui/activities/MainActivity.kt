package com.chilik1020.mustachepaws.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chilik1020.mustachepaws.R
import com.chilik1020.mustachepaws.ui.fragments.LoginFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment_container?.let {
            if (savedInstanceState != null) {
                return
            }

            val loginFragment = LoginFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, loginFragment)
                .commit()
        }
    }
}
