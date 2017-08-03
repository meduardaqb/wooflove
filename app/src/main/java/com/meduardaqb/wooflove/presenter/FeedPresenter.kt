package com.meduardaqb.wooflove.presenter

import android.support.v7.widget.RecyclerView
import com.meduardaqb.wooflove.view.FeedView

interface FeedPresenter {
    fun populateList(list: RecyclerView)
    fun setView(view: FeedView)
}