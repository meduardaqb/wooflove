package com.meduardaqb.wooflove.presenter

import com.afollestad.materialdialogs.MaterialDialog
import com.meduardaqb.wooflove.view.LoginView

interface LoginPresenter {
    fun signIn()
    fun signUp()
    fun setView(view: LoginView)
    fun verifyFields()
    fun resetPassword(): MaterialDialog
}