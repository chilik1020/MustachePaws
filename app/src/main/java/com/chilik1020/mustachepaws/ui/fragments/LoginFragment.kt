package com.chilik1020.mustachepaws.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.chilik1020.mustachepaws.R
import com.chilik1020.mustachepaws.Screens
import com.chilik1020.mustachepaws.presenters.LoginPresenterImpl
import com.chilik1020.mustachepaws.ui.base.BackButtonListener
import com.chilik1020.mustachepaws.utils.APPSCOPE
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnLogin -> {
                val username = tietUsernameLoginF.text.toString()
                val password = tietPasswordLoginF.text.toString()
                presenter.executeLogin(username, password)
            }

            R.id.btnSignUp -> navigateToSignUpFragment()
        }
    }

    override fun render(state: LoginViewState) {
        resetViews()
        when(state) {
            is LoginViewState.LoginLoadingState -> {
                pbLoginLoading.visibility = View.VISIBLE
            }

            is LoginViewState.LoggedState -> {
                Toast.makeText(activity, "You have successfully logged in!", Toast.LENGTH_LONG).show()
                navigateToPostListFragment()
            }

            is LoginViewState.LoginErrorState -> {
                tilUsernameLoginF.error = state.message
                tilPasswordLoginF.error = " "
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }

            is LoginViewState.UsernameErrorState -> {
                tilUsernameLoginF.error = state.message
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }

            is LoginViewState.PasswordErrorState -> {
                tilPasswordLoginF.error = state.message
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun resetViews() {
        pbLoginLoading.visibility = View.GONE
        tilUsernameLoginF.error = null
        tilPasswordLoginF.error = null
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
