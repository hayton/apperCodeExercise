package com.example.appercodetest.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.appercodetest.R

class ItemDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val space = parent.context.resources.getDimension(R.dimen.recyclerview_item_space).toInt()
        outRect.set(0, space, 0, 0)
    }
}