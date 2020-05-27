package com.justclean.samples

import android.view.View
import android.widget.TextView
import com.justclean.core.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

open class SimpleItem : AbstractItem<SimpleItem.ViewHolder>() {
    var name: String? = null
    var description: String? = null

    /** defines the type defining this item. must be unique. preferably an id */
    override val type: Int
        get() = R.id.fastadapter_sample_item_id

    /** defines the layout which will be used for this item in the list */
    override val layoutRes: Int get() = R.layout.sample_item

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : FastAdapter.ViewHolder<SimpleItem>(view) {
        var name: TextView = view.findViewById(R.id.material_drawer_name)
        var description: TextView = view.findViewById(R.id.material_drawer_description)

        override fun bindView(item: SimpleItem, payloads: List<Any>) {
            name.text = item.name
            description.text = item.name
        }

        override fun unbindView(item: SimpleItem) {
            name.text = null
            description.text = null
        }
    }
}