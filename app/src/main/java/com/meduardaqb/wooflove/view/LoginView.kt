package com.meduardaqb.wooflove.view

interface LoginView : BaseView{
    fun getEmail(): String
    fun getPassword(): String
    fun setVisibility(status: Int)
}