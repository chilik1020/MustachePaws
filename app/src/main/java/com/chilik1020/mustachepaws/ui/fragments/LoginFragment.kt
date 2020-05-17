package com.chilik1020.mustachepaws.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.chilik1020.mustachepaws.R
import com.chilik1020.mustachepaws.presenters.LoginPresenter
import com.chilik1020.mustachepaws.presenters.LoginPresenterImpl
import com.chilik1020.mustachepaws.views.LoginView
import com.chilik1020.mustachepaws.viewstates.LoginViewState
import kotlinx.android.synthetic.main.fragment_login.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class LoginFragment : MvpAppCompatFragment(), LoginView, View.OnClickListener {

    @InjectPresenter
    lateinit var presenter : LoginPresenterImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnLogin.setOnClickListener(this)
        tvRegister.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnLogin -> presenter.executeLogin(tietLogin.text.toString(), tietPassword.text.toString())

            R.id.tvRegister -> TODO()
        }
    }


    override fun render(state: LoginViewState) {

        when(state) {
            is LoginViewState.LoginLoadingState -> {
                pbLoginLoading.visibility = View.VISIBLE
            }

            is LoginViewState.LoggedState -> {
                pbLoginLoading.visibility = View.GONE
                Toast.makeText(activity, "You are successfully logged in!", Toast.LENGTH_LONG).show()
            }

            is LoginViewState.LoginErrorState -> {
                pbLoginLoading.visibility = View.GONE
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }
        }
    }

}
