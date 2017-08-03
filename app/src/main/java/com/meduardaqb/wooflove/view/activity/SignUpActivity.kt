package com.meduardaqb.wooflove.view.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatSpinner
import android.view.MenuItem
import com.meduardaqb.wooflove.R
import com.meduardaqb.wooflove.presenter.SignUpPresenter
import com.meduardaqb.wooflove.presenter.implementation.SignUpPresenterImpl
import com.meduardaqb.wooflove.view.SignUpView
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity (val presenter: SignUpPresenter = SignUpPresenterImpl()) : AppCompatActivity(), SignUpView {

    init {
        presenter.setView( this )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        presenter.onCreate()

        sign_up_button.setOnClickListener { presenter.signUp() }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home ->{finish()}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getContext(): Context {
        return this
    }

    override fun getPetName(): String? {
        return petname_edittext.text.toString()
    }

    override fun getSpinner(): AppCompatSpinner {
        return dog_breed_spinner
    }

    override fun getEmail(): String {
        return email_edittext.text.toString()
    }

    override fun getPassword(): String {
        return password_edittext.text.toString()
    }

    override fun getCity(): String? {
        return city_edittext.text.toString()
    }

    override fun getMessage(): String? {
        return message_editext.text.toString()
    }
}
