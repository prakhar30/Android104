package com.example.android104

import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class RecyclerAdapter(private val userdata: ArrayList<UserData>) : RecyclerView.Adapter<RecyclerAdapter.UserDataHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_row, false)
        return UserDataHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return userdata.size
    }

    override fun onBindViewHolder(holder: UserDataHolder, position: Int) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#c9ee82"))
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#eea782"))
        }

        val data = userdata[position]
        holder.bindUserData(data)
    }

    class UserDataHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var userData: UserData? = null

        init {
            v.setOnClickListener(this)
        }

        fun bindUserData(data: UserData) {
            this.userData = data
            view.textViewName.text = data.name
            view.textViewNumber.text = data.phone
            view.textViewEmail.text = data.email
        }

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }
    }
}

data class UserData(val name: String, val phone: String, val email: String)