package com.meduardaqb.wooflove.presenter

import android.support.v7.widget.RecyclerView
import com.meduardaqb.wooflove.view.MyInterestView

interface MyInterestPresenter {
    fun setView( view: MyInterestView )
    fun populate ( list: RecyclerView )
}