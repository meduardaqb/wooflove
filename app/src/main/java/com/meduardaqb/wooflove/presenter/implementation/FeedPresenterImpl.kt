package com.meduardaqb.wooflove.presenter.implementation

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.firebase.database.*
import com.meduardaqb.wooflove.model.User
import com.meduardaqb.wooflove.presenter.FeedPresenter
import com.meduardaqb.wooflove.view.FeedView
import com.meduardaqb.wooflove.view.adapter.FeedAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.meduardaqb.wooflove.util.DatabaseNode

class FeedPresenterImpl : FeedPresenter{

    private lateinit var view: FeedView
    var adapter = FeedAdapter()

    override fun populateList(list: RecyclerView) {

        list.layoutManager = LinearLayoutManager(view.getContext())
        list.adapter = adapter

        val ref = FirebaseDatabase.getInstance().reference.child(DatabaseNode.USER.node)

        ref.addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot?) {
                        dataSnapshot?.children?.map { it.getValue(User::class.java) }?.forEach { adapter.add(it) }
                    }

                    override fun onCancelled(p0: DatabaseError?) {}
                })
    }

    override fun setView(view: FeedView) {
        this.view  = view
    }
}