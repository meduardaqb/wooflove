package com.meduardaqb.wooflove.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.meduardaqb.wooflove.R
import com.meduardaqb.wooflove.model.User
import com.meduardaqb.wooflove.util.DatabaseNode
import com.meduardaqb.wooflove.util.LocalPushNotification
import com.meduardaqb.wooflove.util.PushNotification
import kotlin.properties.Delegates


class FeedAdapter : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    private lateinit var context: Context
    var data: MutableList<User> by Delegates.observable(mutableListOf(), { _, _, _ -> notifyDataSetChanged() })

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.bind(item)
        holder.mark.setOnClickListener {
            if (FirebaseAuth.getInstance()?.currentUser?.email != null) {

                val currentUser = FirebaseAuth.getInstance()?.currentUser?.email?.replace("@", "")?.replace(".", "")
                val pushUtil: PushNotification = LocalPushNotification()
                val message = "Você mostrou interesse em: "+ data[position].name
                val user = data[position]

                FirebaseDatabase.getInstance().reference
                        .child(DatabaseNode.INTEREST.node)
                        .child(user.email.replace("@", "").replace(".", ""))
                        .child(currentUser)
                        .setValue(user)

                pushUtil.sendNotification(context = this.context, message = message)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        this.context = parent.context
        return ViewHolder(layoutInflater.inflate(R.layout.feed_item, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun add(user: User) {
        data.add(user)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dogName = view.findViewById(R.id.dog_name) as TextView
        var breed = view.findViewById(R.id.breed) as TextView
        val city = view.findViewById(R.id.city) as TextView
        val message = view.findViewById(R.id.message) as TextView
        val mark = view.findViewById(R.id.mark) as LinearLayout

        fun bind(user: User) {
            dogName.text = "Nome: "+ user.name
            breed.text = "Raça: "+ user.breed
            city.text = "Cidade: "+ user.city
            message.text = "Mensagem: "+ user.message
        }
    }
}