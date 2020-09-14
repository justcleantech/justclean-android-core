package com.justclean.core.heplers

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Adding swipe item functionality for recycler view item by applying the following steps
 * Define new ItemTouchHelper.SimpleCallback and override the onSwiped and onChildDraw
 * For the onSwiped we use the callback function to send the swiped item view holder
 * For the onChildDraw we are creating drawable color and draw it to fill the item background
 * We also use draw the icon on the canvas with centering it vertically and add horizontal margin
 * At the end are attaching the itemTouchHelper to the receiver recycler view
 * @receiver RecyclerView instance to be extended with the swipe functionality fot it's items
 * @param icon Drawable for the icon image that will be displayed when item swiped
 * @param background Int color for the item that will be shown when item swiped
 * @param margin Float the distance between the icon and start of the item
 * @param onItemSwiped Function<ViewHolder, Unit> callback to get the clicked item viewholder
 */
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
                backgroundDrawable.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
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

    val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
    itemTouchHelper.attachToRecyclerView(this)

}