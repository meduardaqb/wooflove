package com.meduardaqb.wooflove.view

import android.support.v7.widget.AppCompatSpinner

interface SignUpView : BaseView{
    fun getPetName(): String?
    fun getEmail(): String
    fun getPassword(): String
    fun getCity(): String?
    fun getMessage(): String?
    fun getSpinner(): AppCompatSpinner
}