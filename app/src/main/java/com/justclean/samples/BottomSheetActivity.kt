package com.justclean.samples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.justclean.core.JCBottomSheet
import com.justclean.core.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.listeners.ClickEventHook
import kotlinx.android.synthetic.main.activity_bottom_sheet.*

class BottomSheetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)


        var ITEMS = ArrayList<IItem<*>>()
        for (i in 0..2) {
            val item = SimpleItem()
            item.description = "descriptions $i "
            item.name = "name $i "
            ITEMS.add(item)
        }
        for (i in 0..2) {
            val item = SimpleItemTwo()
            ITEMS.add(item)
        }

        jcSheet.setListAdapter(ITEMS)
        jcSheet.setSheetState(BottomSheetBehavior.STATE_EXPANDED)
        jcSheet.setEventsHokes(testClickOne,testClickTwo)
        jcSheet.setMinHeight(400)
        jcSheet.setPeekHeight(300)
        jcSheet.setMaxSheetHeight(800)
        jcSheet.setSheetStyle(JCBottomSheet.SheetStyle.primary)
        jcSheet.withDashIcon(View.VISIBLE)
        jcSheet.withCloseIcon(View.VISIBLE)
        jcSheet.withSheetTitle(true,"JustClean Sheet")
    }
    var testClickTwo = (object : ClickEventHook<IItem<*>>() {
        override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
            return if (viewHolder is SimpleItem.ViewHolder) {
                viewHolder.description
            } else null
        }

        override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<IItem<*>>, item: IItem<*>) {
           Toast.makeText(this@BottomSheetActivity,"Test CLick Two",Toast.LENGTH_SHORT).show()
        }
    })

    var testClickOne = (object : ClickEventHook<IItem<*>>() {
        override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
            return if (viewHolder is SimpleItem.ViewHolder) {
                viewHolder.name
            } else null
        }

        override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<IItem<*>>, item: IItem<*>) {
           Toast.makeText(this@BottomSheetActivity,"Test CLick One",Toast.LENGTH_SHORT).show()
        }
    })

    fun testClick(view: View) {
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show()
    }
}
