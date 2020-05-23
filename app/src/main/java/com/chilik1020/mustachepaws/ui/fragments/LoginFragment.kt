package com.chilik1020.mustachepaws.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.chilik1020.mustachepaws.R
import com.chilik1020.mustachepaws.Screens
import com.chilik1020.mustachepaws.presenters.LoginPresenterImpl
import com.chilik1020.mustachepaws.ui.base.BackButtonListener
import com.chilik1020.mustachepaws.utils.APPSCOPE
import com.chilik1020.mustachepaws.utils.LOG_TAG
import com.chilik1020.mustachepaws.views.LoginView
import com.chilik1020.mustachepaws.viewstates.LoginViewState
import kotlinx.android.synthetic.main.fragment_login.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.terrakok.cicerone.Router
import toothpick.ktp.KTP
import javax.inject.Inject

class LoginFragment : MvpAppCompatFragment(), LoginView, View.OnClickListener, BackButtonListener {

    @Inject
    lateinit var router: Router

    @InjectPresenter
    lateinit var presenter : LoginPresenterImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        KTP.openScope(APPSCOPE).inject(this)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnLogin.setOnClickListener(this)
        tvRegister.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnLogin -> presenter.executeLogin(tietUsernameLoginF.text.toString(), tietPasswordLoginF.text.toString())

            R.id.tvRegister -> navigateToSignUpFragment()
        }
    }

    override fun render(state: LoginViewState) {
        when(state) {
            is LoginViewState.LoginLoadingState -> {
                pbLoginLoading.visibility = View.VISIBLE
            }

            is LoginViewState.LoggedState -> {
                pbLoginLoading.visibility = View.GONE
                Toast.makeText(activity, "You have successfully logged in!", Toast.LENGTH_LONG).show()
                navigateToPostListFragment()
            }

            is LoginViewState.LoginErrorState -> {
                pbLoginLoading.visibility = View.GONE
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun navigateToSignUpFragment() {
        router.navigateTo(Screens.SignUpScreen())
    }

    private fun navigateToPostListFragment() {
        router.replaceScreen(Screens.PostListScreen())
    }

    override fun onBackPressed() {
        router.exit()
    }
}
