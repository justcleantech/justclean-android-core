package com.justclean.core.heplers

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addTouchItemHelper(onItemSwiped: (position: Int, itemView: View) -> Unit) {
    onItemSwiped(0, View(context))
}

fun swipeExtUsage(context: Context) {

    val recycler = RecyclerView(context)

    recycler.addTouchItemHelper { position, itemView ->
        print("$position , $itemView")
    }

}