package com.meduardaqb.wooflove.presenter.implementation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import com.meduardaqb.wooflove.R
import com.meduardaqb.wooflove.presenter.HomePresenter
import com.meduardaqb.wooflove.view.HomeView
import com.meduardaqb.wooflove.view.activity.LoginActivity
import org.jetbrains.anko.startActivity


class HomePresenterImpl : HomePresenter {
    private lateinit var view: HomeView

    override fun setView(view: HomeView) {
       this.view = view
    }

    override fun logout() {
        FirebaseAuth.getInstance().signOut()
        view.getContext().startActivity<LoginActivity>()
    }

    override fun setFragment(fragmentManager: FragmentManager, fragment: Fragment) {
        fragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
    }
}