package com.justclean.core.ui.items

import android.view.View
import com.justclean.core.R

import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

open class SimpleItemTwo : AbstractItem<SimpleItemTwo.ViewHolder>() {
    var name: String? = null
    var description: String? = null

    /** defines the type defining this item. must be unique. preferably an id */
    override val type: Int
        get() = R.id.fastadapter_sample_item_two_id

    /** defines the layout which will be used for this item in the list */
    override val layoutRes: Int get() = R.layout.sample_item_two

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : FastAdapter.ViewHolder<SimpleItemTwo>(view) {

        override fun bindView(item: SimpleItemTwo, payloads: List<Any>) {
          //
        }

        override fun unbindView(item: SimpleItemTwo) {
         //
        }
    }
}