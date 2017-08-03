package com.meduardaqb.wooflove.presenter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.meduardaqb.wooflove.view.HomeView

interface HomePresenter {
    fun setFragment(fragmentManager: FragmentManager, fragment: Fragment)
    fun logout()
    fun setView(view: HomeView)
}