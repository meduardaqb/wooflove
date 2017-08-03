package com.meduardaqb.wooflove.view.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.meduardaqb.wooflove.R
import com.meduardaqb.wooflove.presenter.LoginPresenter
import com.meduardaqb.wooflove.presenter.implementation.LoginPresenterImpl
import com.meduardaqb.wooflove.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity (val presenter: LoginPresenter = LoginPresenterImpl()): AppCompatActivity(), LoginView {

    init {
        presenter.setView( this )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sign_in_button.setOnClickListener { presenter.signIn() }
        sign_up_button.setOnClickListener { presenter.signUp() }
        reset_password_button.setOnClickListener { presenter.resetPassword().show()}
    }

    override fun getContext(): Context {
        return this
    }

    override fun getEmail(): String {
        return email_edittext.text.toString()
    }

    override fun getPassword(): String {
        return password_edittext.text.toString()
    }

    override fun setVisibility(status: Int) {
        login_progress.visibility = status
    }

    override fun onBackPressed() {}
}
