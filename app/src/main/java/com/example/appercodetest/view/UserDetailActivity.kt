package com.example.appercodetest.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.appercodetest.R
import com.example.appercodetest.model.UserDetail
import com.example.appercodetest.viewmodel.UserDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.user_detail.*

@AndroidEntryPoint
class UserDetailActivity: AppCompatActivity() {
    val viewModel by viewModels<UserDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail)

        val url = intent.getStringExtra("url") ?:""
        viewModel.getUserDetail(url).observe(this) {
            updateUi(it)
        }

    }

    fun updateUi(userDetail: UserDetail) {
        Glide.with(this)
            .asBitmap()
            .load(userDetail.avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(icon)

        personicon.visibility = View.VISIBLE
        name.text = userDetail.name
        loginname.text = userDetail.login
        if (userDetail.isSiteAdmin) {
            adminBadge.visibility = View.VISIBLE
            adminBadge.text = userDetail.type
        } else {
            adminBadge.visibility = View.GONE
        }

        close.setOnClickListener {
            onBackPressed()
        }
    }
}