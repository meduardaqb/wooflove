package com.meduardaqb.wooflove.view.activity

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.meduardaqb.wooflove.R
import com.meduardaqb.wooflove.presenter.HomePresenter
import com.meduardaqb.wooflove.presenter.implementation.HomePresenterImpl
import com.meduardaqb.wooflove.view.HomeView
import com.meduardaqb.wooflove.view.fragment.FeedFragment
import com.meduardaqb.wooflove.view.fragment.MyInterestFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity (val presenter: HomePresenter = HomePresenterImpl()): AppCompatActivity(), HomeView {

    init {
        presenter.setView( this )
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                presenter.setFragment(supportFragmentManager, FeedFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                presenter.setFragment(supportFragmentManager, MyInterestFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter.setFragment(supportFragmentManager, FeedFragment())
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_logout) {
            presenter.logout()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun getContext(): Context {
        return this
    }

    override fun onBackPressed() {}

}
