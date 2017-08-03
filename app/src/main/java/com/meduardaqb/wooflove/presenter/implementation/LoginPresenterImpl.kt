package com.meduardaqb.wooflove.presenter.implementation

import android.app.Activity
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.meduardaqb.wooflove.exception.BlankFieldException
import com.meduardaqb.wooflove.presenter.LoginPresenter
import com.meduardaqb.wooflove.view.LoginView
import com.meduardaqb.wooflove.view.activity.HomeActivity
import com.meduardaqb.wooflove.view.activity.SignUpActivity
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import android.widget.EditText
import android.support.v4.content.ContextCompat
import com.meduardaqb.wooflove.R


class LoginPresenterImpl: LoginPresenter {

    private lateinit var view: LoginView

    override fun setView(view: LoginView) {
       this.view = view
    }

    override fun signUp() {
       view.getContext().startActivity<SignUpActivity>()
    }

    override fun signIn() {

        try {

            this.verifyFields()

            view.setVisibility(View.VISIBLE)

            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(view.getEmail(), view.getPassword())
                    .addOnCompleteListener { task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            view.setVisibility(View.GONE)
                            val context = view.getContext() as Activity
                            view.getContext().startActivity<HomeActivity>()
                            context.finish()
                        } else {
                            view.setVisibility(View.GONE)
                            view.getContext().toast("Erro ao tentar efeturar o login")
                        }
                    }

        } catch (e: BlankFieldException) {
            view.getContext().toast("Você precisa preencher todos campos para efetuar o login")
        }
    }

    override fun verifyFields() {
        if ( view.getEmail().isNullOrEmpty() || view.getPassword().isNullOrEmpty() ){
            throw BlankFieldException()
        }
    }

    override fun resetPassword(): MaterialDialog {

        val materialDialog = MaterialDialog.Builder( this.view.getContext() )
                .title(R.string.reset_password_dialog_title)
                .backgroundColor(ContextCompat.getColor(this.view.getContext(), android.R.color.white))
                .contentColor(ContextCompat.getColor(this.view.getContext(), android.R.color.black))
                .customView(R.layout.reset_password_dialog, true)
                .positiveText(R.string.reset)
                .negativeText(android.R.string.cancel)
                .onPositive({ dialog, _ ->
                    val email = dialog.customView!!.findViewById(R.id.email_edittext) as EditText

                    if (!email.text.isNullOrEmpty()) {
                        val auth = FirebaseAuth.getInstance()
                        auth.sendPasswordResetEmail(email.text.toString().replace(" ", ""))
                        dialog.dismiss()
                    } else {
                        this.view.getContext().toast(R.string.email_is_requeried_reset_password)
                    }
                })
                .build()

        return materialDialog

    }
}