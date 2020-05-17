package com.chilik1020.mustachepaws.rules

import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.test.rule.ActivityTestRule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class UnlockScreenRule <A : AppCompatActivity> (val activityTestRule: ActivityTestRule<A>) : TestRule {

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                activityTestRule.runOnUiThread {
                    activityTestRule
                        .activity
                        .window
                        .addFlags(
                            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                                    or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                                    or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                                    or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                                    or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
                        )
                }
                base?.evaluate()
            }
        }
    }
}