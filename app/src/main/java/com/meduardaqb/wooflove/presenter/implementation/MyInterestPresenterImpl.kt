package com.meduardaqb.wooflove.presenter.implementation

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.meduardaqb.wooflove.model.User
import com.meduardaqb.wooflove.presenter.MyInterestPresenter
import com.meduardaqb.wooflove.util.DatabaseNode
import com.meduardaqb.wooflove.view.MyInterestView
import com.meduardaqb.wooflove.view.adapter.MyInterestAdapter

class MyInterestPresenterImpl : MyInterestPresenter {
    private lateinit var view: MyInterestView
    var adapter = MyInterestAdapter()

    override fun setView(view: MyInterestView) {
        this.view = view
    }

    override fun populate(list: RecyclerView) {

        list.layoutManager = LinearLayoutManager(view.getContext())
        list.adapter = adapter
        val currentUser = FirebaseAuth.getInstance()?.currentUser?.email?.replace("@", "")?.replace(".", "")

        val ref = FirebaseDatabase.getInstance().reference.child(DatabaseNode.INTEREST.node).child(currentUser)

        ref.addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot?) {
                        dataSnapshot?.children?.map { it.getValue(User::class.java) }?.forEach { adapter.add(it) }
                    }

                    override fun onCancelled(p0: DatabaseError?) {}
                })

    }
}