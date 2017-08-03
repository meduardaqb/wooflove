package com.meduardaqb.wooflove.presenter

import com.meduardaqb.wooflove.model.User
import com.meduardaqb.wooflove.view.SignUpView

interface SignUpPresenter {
    fun onCreate()
    fun setView(view: SignUpView)
    fun populateSpinner()
    fun signUp()
    fun verifyFields()
    fun registerUserIntoAuth(user: User)
    fun registerUserIntoDatabase(user: User)
}