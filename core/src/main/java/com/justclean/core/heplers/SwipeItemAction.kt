package com.justclean.core.heplers

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addSwipeItemAction(
    icon: Drawable, background: Int, margin: Float? = null,
    onItemSwiped: (viewHolder: RecyclerView.ViewHolder) -> Unit
) {

    val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                onItemSwiped(viewHolder)
            }

            override fun onChildDraw(
                c: Canvas, recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float, dY: Float, actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if (viewHolder.adapterPosition == -1) return

                val itemView = viewHolder.itemView

                val backgroundDrawable = ColorDrawable(background)
                backgroundDrawable.setBounds(
                    itemView.right + dX.toInt(),
                    itemView.top,
                    itemView.right,
                    itemView.bottom
                )
                backgroundDrawable.draw(c)

                val itemHeight = itemView.bottom - itemView.top
                val intrinsicWidth = icon.intrinsicWidth
                val intrinsicHeight = icon.intrinsicHeight
                val iconMargin = margin ?: 50f
                val iconLeft = itemView.right - iconMargin - intrinsicWidth
                val iconRight = itemView.right - iconMargin
                val iconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
                val iconBottom = iconTop + intrinsicHeight

                icon.setBounds(iconLeft.toInt(), iconTop, iconRight.toInt(), iconBottom)
                icon.draw(c)

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

        }

    val mItemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
    mItemTouchHelper.attachToRecyclerView(this)

}