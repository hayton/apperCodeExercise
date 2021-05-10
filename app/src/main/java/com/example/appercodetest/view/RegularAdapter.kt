package com.example.appercodetest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appercodetest.R
import com.example.appercodetest.model.User

class RegularAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var dataList = arrayListOf<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserViewHolder(inflater.inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserViewHolder) {
            holder.setUser(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}