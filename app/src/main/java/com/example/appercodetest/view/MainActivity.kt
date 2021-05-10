package com.example.appercodetest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appercodetest.R
import com.example.appercodetest.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = RegularAdapter()
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycleView.addItemDecoration(ItemDecoration())

        viewModel.getUsersList().observe(this@MainActivity) {
            adapter.dataList = it
            adapter.notifyDataSetChanged()
        }

//        viewModel.getUsersList()
    }
}