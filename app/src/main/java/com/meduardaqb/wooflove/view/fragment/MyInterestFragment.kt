package com.meduardaqb.wooflove.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meduardaqb.wooflove.R
import com.meduardaqb.wooflove.presenter.MyInterestPresenter
import com.meduardaqb.wooflove.presenter.implementation.MyInterestPresenterImpl
import com.meduardaqb.wooflove.view.MyInterestView

class MyInterestFragment (val presenter: MyInterestPresenter = MyInterestPresenterImpl()) : Fragment(), MyInterestView {

    init {
        presenter.setView( this )
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_interest, container, false)

        val mRecyclerView = view.findViewById(R.id.feed_recyclerview) as RecyclerView
        presenter.populate(mRecyclerView)

        return view
    }

    override fun getContext(): Context {
        return activity
    }
}