package com.chilik1020.mustachepaws.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.chilik1020.mustachepaws.R

fun openFragment(activity: AppCompatActivity, fragment: Fragment) {
    activity
        .supportFragmentManager
        .beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .commitAllowingStateLoss()
}