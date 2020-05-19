package com.chilik1020.mustachepaws.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.chilik1020.mustachepaws.R
import com.chilik1020.mustachepaws.models.data.UserRequestObject
import com.chilik1020.mustachepaws.presenters.SignUpPresenterImpl
import com.chilik1020.mustachepaws.views.SignUpView
import com.chilik1020.mustachepaws.viewstates.SignUpViewState
import kotlinx.android.synthetic.main.fragment_sign_up.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class SignUpFragment : MvpAppCompatFragment(), SignUpView, View.OnClickListener {

    @InjectPresenter
    lateinit var presenter: SignUpPresenterImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnSignUp.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnSignUp -> {
                presenter.executeSignUp(UserRequestObject(
                    tietUsernameSignUpF.text.toString(),
                    tietFirstnameSignUpF.text.toString(),
                    tietLastnameSignUpF.text.toString(),
                    tietEmailSignUpF.text.toString(),
                    tietPhonenumberSignUpF.text.toString(),
                    tietPasswordSignUpF.text.toString()
                ))
            }
        }
    }

    override fun render(state: SignUpViewState) {
        when(state) {
            is SignUpViewState.SignUpLoadingState ->
                pbSignUpLoading.visibility = View.VISIBLE

            is SignUpViewState.SignUpFinishedState -> {
                pbSignUpLoading.visibility = View.GONE
                Toast.makeText(activity, "You have successfully registered!", Toast.LENGTH_LONG).show()
                navigateToPostListFragment()
            }

            is SignUpViewState.SignUpErrorState -> {
                pbSignUpLoading.visibility = View.GONE
                Toast.makeText(activity, state.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun navigateToPostListFragment() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, PostListFragment())
            ?.commit()
    }
}
