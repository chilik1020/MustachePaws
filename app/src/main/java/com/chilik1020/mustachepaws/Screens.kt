package com.chilik1020.mustachepaws

import androidx.fragment.app.Fragment
import com.chilik1020.mustachepaws.ui.fragments.CreatePostFragment
import com.chilik1020.mustachepaws.ui.fragments.LoginFragment
import com.chilik1020.mustachepaws.ui.fragments.PostListFragment
import com.chilik1020.mustachepaws.ui.fragments.SignUpFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


class Screens {

    class LoginScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return LoginFragment()
        }
    }

    class SignUpScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return SignUpFragment()
        }
    }

    class PostListScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return PostListFragment()
        }
    }

    class CreatePostScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return CreatePostFragment()
        }
    }
}