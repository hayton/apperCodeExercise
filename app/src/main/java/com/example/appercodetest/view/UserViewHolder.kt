package com.example.appercodetest.view

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.appercodetest.R
import com.example.appercodetest.model.User

class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val name = itemView.findViewById<TextView>(R.id.name)
    val icon = itemView.findViewById<ImageView>(R.id.icon)
    val adminBadge = itemView.findViewById<TextView>(R.id.adminBadge)

    fun setUser(user: User?) {
        user?.let { user ->
            name.text = user.login
            Glide.with(itemView)
                .asBitmap()
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .load(user.avatarUrl)
                .into(icon)
            if (user.isSiteAdmin) {
                adminBadge.visibility = View.VISIBLE
                adminBadge.text = user.type
            } else {
                adminBadge.visibility = View.GONE
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, UserDetailActivity::class.java).apply {
                    putExtra("url", user.url)
                }
                itemView.context.startActivity(intent)
            }
        }
    }

}