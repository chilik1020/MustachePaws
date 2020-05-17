package com.chilik1020.mustachepaws.rules

import androidx.appcompat.app.AppCompatActivity
import androidx.test.rule.ActivityTestRule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ActivityTestRule <A : AppCompatActivity>(activityClass: Class<A>) : TestRule {
    var activityRule: ActivityTestRule<A> = ActivityTestRule(activityClass)
    private var ruleChain: RuleChain

    init {
        ruleChain = RuleChain
            .outerRule(activityRule)
            .around(UnlockScreenRule(activityRule))
    }

    fun runOnUiThread(runnable: Runnable) {
        activityRule.runOnUiThread(runnable)
    }

    fun getActivity() = activityRule.activity

    override fun apply(base: Statement?, description: Description?): Statement {
        return ruleChain.apply(base, description)
    }

}