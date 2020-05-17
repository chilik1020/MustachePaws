package com.chilik1020.mustachepaws.rules

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.chilik1020.mustachepaws.utils.openFragment
import org.awaitility.Awaitility.await
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.TimeUnit

class OpenFragmentRule <A : AppCompatActivity> (activityRule: ActivityTestRule<A>, fragment: Fragment) : TestRule {

    var activityRule: ActivityTestRule<A> = activityRule
    var fragment = fragment
    private val timeout = 5L

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                openFragment(activityRule.getActivity(), fragment)
                await().atMost(timeout, TimeUnit.SECONDS).until(fragment::isResumed)
                base?.evaluate()
            }
        }
    }
}